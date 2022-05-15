<template>
  <el-container style="min-height: 100vh">
    <el-aside :width="sideWidth + 'px'" style="background-color: rgb(128,175,255); box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
      <el-menu :default-openeds="['1', '3']"
               style="min-height: 100%; overflow-x: hidden"
               background-color="rgb(48,65,86)"
               text-color="#fff"
               active-text-color="#ffd04b"
               :collapse-transition="false"
               :collapse="isCollapse"
      >
        <div style="height: 60px; line-height: 60px; text-align: center">
          <img src="@/assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px;">
          <b style="color: white; margin-left: 5px" v-show="logoShow">Cluster MS</b>
        </div>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-message"></i>
            <span>Navigator 1</span>
          </template>
          <el-menu-item-group>
            <template slot="title">Group 1</template>
            <el-menu-item index="1-1">Option 1</el-menu-item>
            <el-menu-item index="1-2">Option 2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="Group 2">
            <el-menu-item index="1-3">Option 3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template slot="title">Option4</template>
            <el-menu-item index="1-4-1">Option 4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span>Navigator 2</span>
          </template>
          <el-menu-item-group>
            <template slot="title">Group 1</template>
            <el-menu-item index="2-1">Option 1</el-menu-item>
            <el-menu-item index="2-2">Option 2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="Group 2">
            <el-menu-item index="2-3">Option 3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="2-4">
            <template slot="title">Option 4</template>
            <el-menu-item index="2-4-1">Option 4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-setting"></i>
            <span>Navigator 3</span>
          </template>
          <el-submenu index="3-4">
            <template slot="title">Option 4</template>
            <el-menu-item index="3-4-1">Option 4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #cccccc; line-height:60px; display: flex">
        <div style="flex: 1; font-size: 20px">
          <span :class="collapseBtnClass" style="cursor:pointer" @click="collapse"></span>
        </div>
        <el-dropdown style="width: 70px; cursor: pointer">
          <span>Tom</span>
          <i class="el-icon-arrow-down" style="margin-left: 5px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>Account</el-dropdown-item>
            <el-dropdown-item>Log out</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <el-main style="padding-top: 10px">
        <div style="margin-bottom: 10px">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">Home</el-breadcrumb-item>
            <el-breadcrumb-item>User Management</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        <div style="padding: 10px 0">
<!--          <el-input style="width: 350px" placeholder="Use '+' to combine multiple keywords" suffix-icon="el-icon-search"></el-input>-->
          <el-input style="width: 200px" placeholder="Enter username" suffix-icon="el-icon-user" v-model="username"></el-input>
          <el-input style="width: 200px" placeholder="Enter e-mail" suffix-icon="el-icon-message" class="ml-5" v-model="email"></el-input>
          <el-input style="width: 200px" placeholder="Enter address" suffix-icon="el-icon-position" class="ml-5" v-model="address"></el-input>
          <el-button class="ml-5" type="primary" @click="load">Search</el-button>
          <el-button class="ml-5" type="warning" @click="clear">Show All</el-button>
        </div>
        <div style="margin: 10px 0">
          <el-button type="primary" @click="handleAdd">Add <i class="el-icon-circle-plus-outline"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='Confirm'
              cancel-button-text='Cancel'
              icon="el-icon-info"
              icon-color="red"
              title="Are you sure?"
              @confirm="handleDeleteBatch"
          >
            <el-button type="danger" slot="reference">Multiple delete <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
          <el-button type="primary">Import <i class="el-icon-upload2"></i></el-button>
          <el-button type="primary">Export <i class="el-icon-download"></i></el-button>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="headerClass" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="id" label="ID" width="80">
          </el-table-column>
          <el-table-column prop="username" label="Username" width="140">
          </el-table-column>
          <el-table-column prop="nickname" label="Nickname" width="140">
          </el-table-column>
          <el-table-column prop="email" label="Email" width="140">
          </el-table-column>
          <el-table-column prop="phone" label="Phone" width="140">
          </el-table-column>
          <el-table-column prop="address" label="Address" width="140">
          </el-table-column>
          <el-table-column label="Operation">
            <template v-slot="scope">
              <el-button type="success" @click="handleEdit(scope.row)">Edit<i class="el-icon-edit-outline" style="margin-left: 5px"></i></el-button>
<!--              confirm-->
              <el-popconfirm
                  class="ml-5"
                  confirm-button-text='Confirm'
                  cancel-button-text='Cancel'
                  icon="el-icon-info"
                  icon-color="red"
                  title="Are you sure?"
                  @confirm="handleDelete(scope.row.id)"
              >
                <el-button type="danger" slot="reference">Delete<i class="el-icon-document-delete" style="margin-left: 5px"></i></el-button>
              </el-popconfirm>
            </template>
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

<!--        dialog-->
        <el-dialog title="Item Information" :visible.sync="dialogFormVisible" width="30%" >
          <el-form label-width="80px" size="small">
            <el-form-item label="username">
              <el-input v-model="form.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="nickname">
              <el-input v-model="form.nickname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="email">
              <el-input v-model="form.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="phone">
              <el-input v-model="form.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="address">
              <el-input v-model="form.address" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">Cancel</el-button>
            <el-button type="primary" @click="save">Submit</el-button>
          </div>
        </el-dialog>

      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'HomeView',
  data() {
    // default row for showing
    // const item = {
    //   date: '2016-05-02',
    //   name: 'Tom',
    //   address: 'No. 189, Grove St, Los Angeles'
    // };
    return {
      msg: "hello Mingzhe!!! : )",
      tableData: [],
      multipleSelection: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      username: "",
      email: "",
      address: "",
      form: {},
      dialogFormVisible: false,
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoShow: true,
      headerClass: 'headerClass'
    }
  },
  created() {
    this.load()
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
      request.get("sys-user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address,
        }
      }).then(res =>
      {
        this.tableData = res.records
        this.total = res.total
      })
    },
    clear() {
      this.username = ""
      this.email = ""
      this.address = ""
      this.load()
    },
    handleAdd() {
      this.dialogFormVisible = true;
      this.form = {}
    },
    save() {
      request.post("sys-user", this.form).then(res => {
        if (res) {
          this.$message.success("Save Successfully")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("Save Fails")
        }
      })
    },
    handleEdit(row) {
      this.form = row;
      this.dialogFormVisible = true;
    },
    handleDelete(id) {
      request.delete("sys-user/" + id).then(res => {
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
      request.post("sys-user/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("Delete Successfully")
          this.load()
        } else {
          this.$message.error("Delete Fails")
        }
      })
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    }
  }
}
</script>

<style>
.headerClass {
  background: #eee !important;
}
</style>
