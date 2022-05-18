<template>
  <el-container style="min-height: 100vh">
    <el-aside :width="sideWidth + 'px'" style="background-color: rgb(128,175,255); box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
      <el-menu :default-openeds="['1', '2']"
               style="min-height: 100%; overflow-x: hidden"
               background-color="rgb(48,65,86)"
               text-color="#fff"
               active-text-color="#ffd04b"
               :collapse-transition="false"
               :collapse="isCollapse"
               router
      >
        <div style="height: 60px; line-height: 60px; text-align: center">
          <img src="@/assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px;">
          <b style="color: white; margin-left: 5px" v-show="logoShow">Logistic</b>
        </div>

        <el-menu-item index="/home">
          <i class="el-icon-s-home"></i>
          <span>Home</span>
        </el-menu-item>

        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span>Product</span>
          </template>

          <el-menu-item index="/">Inventory</el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #cccccc; line-height:60px; display: flex">
        <div style="flex: 1; font-size: 20px">
          <span :class="collapseBtnClass" style="cursor:pointer" @click="collapse"></span>
        </div>
        <el-dropdown style="width: 70px; cursor: pointer">
          <span>ROOT</span>
          <i class="el-icon-arrow-down" style="margin-left: 5px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>Account</el-dropdown-item>
            <el-dropdown-item>Log out</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <el-main style="padding-top: 10px">
        <div style="padding: 10px 0">
<!--          <el-input style="width: 350px" placeholder="Use '+' to combine multiple keywords" suffix-icon="el-icon-search"></el-input>-->
          <el-input style="width: 200px" placeholder="Enter product" suffix-icon="el-icon-user" v-model="product"></el-input>
          <el-input style="width: 200px" placeholder="Enter SKU" suffix-icon="el-icon-message" class="ml-5" v-model="sku"></el-input>
          <el-input style="width: 200px" placeholder="Enter type" suffix-icon="el-icon-position" class="ml-5" v-model="type"></el-input>
          <el-button class="ml-5" type="primary" @click="load">Search</el-button>
          <el-button class="ml-5" type="warning" @click="clear">Show All</el-button>
        </div>
        <div style="margin: 10px 0">
          <el-button v-if="!showDeleted" type="primary" @click="handleAdd">Add <i class="el-icon-circle-plus-outline"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='Confirm'
              cancel-button-text='Cancel'
              icon="el-icon-info"
              icon-color="red"
              title="Are you sure?"
              @confirm="handleDeleteBatch"
          >
            <el-button v-if="!showDeleted" type="danger" slot="reference" style="margin-right: 10px">Multiple delete <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
          <el-button type="primary">Import <i class="el-icon-upload2"></i></el-button>
          <el-button type="primary">Export <i class="el-icon-download"></i></el-button>
          <el-switch  style="margin-left: 200px"
              v-model="showDeleted"
              class="mb-2"
              size="large"
              active-text="Show Deleted"
          />
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="headerClass" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="id" label="ID" width="80">
          </el-table-column>
          <el-table-column prop="product" label="product" width="140">
          </el-table-column>
          <el-table-column prop="sku" label="sku" width="140">
          </el-table-column>
          <el-table-column prop="type" label="type" width="140">
          </el-table-column>
          <el-table-column prop="quality" label="quality" width="140">
          </el-table-column>
          <el-table-column label="Operation">
            <template v-slot="scope">
              <el-button type="success" v-if="!showDeleted"  @click="handleEdit(scope.row)">Edit<i class="el-icon-edit-outline" style="margin-left: 5px"></i></el-button>
<!--              confirm-->
              <el-popconfirm
                  class="ml-5"
                  confirm-button-text='Confirm'
                  cancel-button-text='Cancel'
                  icon="el-icon-info"
                  icon-color="red"
                  title="Are you sure?"
                  @confirm="handleDelete(scope.row)"
              >
                <el-button type="danger" slot="reference" v-if="!showDeleted">Delete<i class="el-icon-document-delete" style="margin-left: 5px"></i></el-button>
              </el-popconfirm>
              <el-button type="success" v-if="showDeleted" @click="handleRestore(scope.row)">Restore<i class="el-icon-edit-outline" style="margin-left: 5px"></i></el-button>
            </template>
          </el-table-column>
          <el-table-column v-if="showDeleted" prop="comment" label="Deletion Comment" width="140">
          </el-table-column>
        </el-table>
        <div style="padding: 10px 0">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[5, 10, 15, 20]"
              :page-size="5"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
        </div>

        <!--   edit dialog-->
        <el-dialog title="Item Information" :visible.sync="editFormVisible" width="30%" >
          <el-form label-width="80px" size="small">
            <el-form-item label="product">
              <el-input v-model="form.product" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="SKU">
              <el-input v-model="form.sku" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="type">
              <el-input v-model="form.type" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="quality">
              <el-input v-model="form.quality" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="editFormVisible = false">Cancel</el-button>
            <el-button type="primary" @click="save">Submit</el-button>
          </div>
        </el-dialog>

        <!--   delete dialog-->
        <el-dialog title="Deletion Comment (Optional)" :visible.sync="deleteFormVisible" width="30%" >
          <el-form label-width="80px" size="small">
            <el-form-item label="Leave your reason for deleting here.">
              <el-input v-model="form.comment" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="deleteFormVisible = false">Cancel</el-button>
            <el-button type="primary" @click="saveDeleteComment">Submit</el-button>
          </div>
        </el-dialog>

      </el-main>
    </el-container>
  </el-container>
</template>

<script>

export default {
  name: 'HomeView',
  data() {
    // default row for showing
    // const item = {
    //   date: '2016-05-02',
    //   name: 'Tom',
    //   type: 'No. 189, Grove St, Los Angeles'
    // };
    return {
      msg: "hello Mingzhe!!! : )",
      tableData: [],
      multipleSelection: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      product: "",
      sku: "",
      type: "",
      comment: "",
      form: {},
      editFormVisible: false,
      deleteFormVisible: false,
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoShow: true,
      headerClass: 'headerClass',
      showDeleted: false,
    }
  },
  created() {
    this.load()
  },
  watch: {
    showDeleted: function (wantShowDeleted) {
      if (wantShowDeleted) {
        // console.log("yes")
        this.loadDeleted();

      } else {
        // console.log("no")
        this.load();

      }
    }
  },
  methods: {
    collapse() {
      this.isCollapse = !this.isCollapse;
      if (this.isCollapse) {
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoShow = false
      } else {
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoShow = true
      }
    },
    load() {
      // request pagination data
      this.request.get("sys-user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          product: this.product,
          sku: this.sku,
          type: this.type,
        }
      }).then(res =>
      {
        this.tableData = res.records
        this.total = res.total
      })
    },
    loadDeleted() {
      // request pagination data
      this.request.get("sys-user/page/deleted", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          product: this.product,
          sku: this.sku,
          type: this.type,
        }
      }).then(res =>
      {
        console.log(res.records)
        this.tableData = res.records
        this.total = res.total
      })
    },
    clear() {
      this.product = ""
      this.sku = ""
      this.type = ""
      this.load()
    },
    handleAdd() {
      this.editFormVisible = true;
      this.form = {}
    },
    save() {
      // console.log(this.form)
      this.request.post("sys-user", this.form).then(res => {
        if (res) {
          this.$message.success("Save Successfully")
          this.editFormVisible = false
          this.load()
        } else {
          this.$message.error("Save Fails")
        }
      })
    },
    handleEdit(row) {
      this.form = row;
      this.editFormVisible = true;
    },
    // restore data
    handleRestore(row) {
      this.form = row;
      this.request.get("sys-user/restore", {
        params: {
          id: row.id
        }
      }).then(res => {
        if (res) {
          this.$message.success("Restore Successfully")
          this.loadDeleted();
        } else {
          this.$message.error("Restore Fails")
        }
      })
    },
    // use another port mapping to handle deletion comment feature
    saveDeleteComment() {
      // console.log(this.form)
      console.log(this.form)
      this.request.post("sys-user/deletionComment", this.form).then(res => {
        if (res) {
          this.$message.success("Save Successfully")
          this.deleteFormVisible = false
          this.load()
        } else {
          this.$message.error("Save Fails")
        }
      })
    },
    handleDelete(row) {
      this.form = row;
      // pop out the deletion comment dialog
      this.deleteFormVisible = true;

      this.request.delete("sys-user/" + row.id).then(res => {
        if (res) {
          this.$message.success("Delete Successfully")
          this.load()
        } else {
          this.$message.error("Delete Fails")
        }
      })
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleDeleteBatch() {
      // flatten:
      // [{}, {}, {}] => [1,2,3]
      let ids = this.multipleSelection.map(v => v.id)
      this.request.post("sys-user/del/batch", ids).then(res => {
        if (res) {
          console.log(ids)
          this.$message.success("Delete Successfully")
          this.load()
        } else {
          this.$message.error("Delete Fails")
        }
      })
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      if (this.showDeleted) {
        this.loadDeleted();
      } else {
        this.load()
      }

    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      if (this.showDeleted) {
        this.loadDeleted();
      } else {
        this.load()
      }
    }
  }
}
</script>

<style>
.headerClass {
  background: #eee !important;
}
</style>
