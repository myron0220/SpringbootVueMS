# SpringbootVueMS

<a href="https://drive.google.com/uc?export=view&id=1qVz2EXmIZCi697SQFHvcB1XV7TJpnjN_">
  <img src="https://drive.google.com/uc?export=view&id=1qVz2EXmIZCi697SQFHvcB1XV7TJpnjN_" style="width: 650px; max-width: 100%; height: auto" title="Click to enlarge picture" />
</a>


A frontend and backend seperated inventory management system implemented with Springboot and Vue.

## 1. Deploy on Replit
- In your browswer, open the following link in a new tab:  
`https://replit.com/@myron0220/SpringbootVueMS#.replit`  
Then click the "Run" button in Replit.  

- Don't close previous tab, open the following link in a new tab:  
`https://replit.com/@myron0220/SpringbootVueMS-1#.replit`  
Then click the "Run" button in Replit.  
Note: the "Whitelabel Error Page" doesn't matter, as I will not display my website in Replit built-in window.

- Don't close previous tab, waiting for the build finish of previous two tabs, then open the following link in a new tab:  
`https://springbootvuems.myron0220.repl.co/`  
This page is the main (ONLY) UI for the website.

## 2. Usage
- For CRUD usages, explore the website by yourself.  
(Note due to the time limie, "import" and "export" buttons haven't been activated. Sorry.)  

- For the additional feature (the "When deleting, allow deletion comments and undeletion" is choosen)  
When deleting a row, the deletion comment dialog will be automatically popped up. You can choose to leave your comment for that deletion.  
Use the "Show Deleted" switch to show previously deleted items. Then you can use "restore" to undelete that item.

## 3. Appendix
- The database is run separetely on Google clound, as Replit's terminal seems to response lagly.
- Important!!! I notice sometime the replit servers are not working correctly. If this case happends, you may also build this project on your local machine:  
1. install npm and mvn.
2. `git clone "https://github.com/myron0220/SpringbootVueMS.git"`
3. make sure you pwd is `*/SpringbootVueMS`, if not `cd SpringbootVueMS`
4. `bash 'CORS&request_FOR_LOCAL_SETUP/local_setup.bash'`
5. open a new terminal at `*/SpringbootVueMS`, run `bash frontend/frontend_setup.bash`.
6. don't close previous terminal, open a new terminal at `*/SpringbootVueMS`, run `bash backend/backend_setup.bash`.
7. don't close previous terminal and wait for their setting up finish, then open `http://localhost:8181/` in your browser
