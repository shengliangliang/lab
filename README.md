# This is a project for myself to play


git commands:  

1，删除远程文件及文件夹  

git rm --cached  filename  
git rm -r --cached dir  

git commit -m ""  
git push origin master  

2,恢复错误删除的本地文件  

git log   to  find the commit id you want to reset  

git reset commit id  
git commit -m ""  
git push origin master  


…or create a new repository on the command line  
echo "# boot" >> README.md  
git init  
git add README.md  
git commit -m "first commit"  
git remote add origin git@github.com:shengliangliang/boot.git  
git push -u origin master  

... fatal: Authentication failed for...(不弹出用户名密码错误)
git config --system --unset credential.helper   （每次提交都需要用户名密码）


…or push an existing repository from the command line  
git remote add origin git@github.com:shengliangliang/boot.git  
git push -u origin master  

…or import code from another repository  
You can initialize this repository with code from a Subversion, Mercurial, or TFS project.  

查看远程仓库
git remote -v  

删除远程仓库关联  
git remote remove origin 

从本地修改远程仓库名称
git remote rename pb paul  
本地的远程仓库关联也会修改  

删除本地分支  
git branch -d 分支名称  

删除远程分支  
git push origin --delete 分支名称  

