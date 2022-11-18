## git介绍

#### 什么是Git

​	Git是一个项目管理工具，用于版本控制，管理代码

#### 什么是版本控制

​	版本控制是一种记录文件修改的历史记录，从而让用户能够查看历史版本，方便版本的切换；

#### 为什么需要版本控制

​	个人开发过渡到团队协作

#### git和svn的区别

* svn
  * 集中式版本控制系统，版本库放在中央服务器
  * 缺点：服务器单点故障，容错性差，即中央服务器发生故障，就无法使用了
* git：
  * 分布式版本控制系统，分为两种类型的仓库
  * 本地仓库：自己电脑上的Git仓库
  * 远程仓库：远程服务器上的Git仓库

#### Git工作流程

1. 从远程仓库克隆代码到本地仓库
2. 从本地仓库创建分支然后进行代码修改
3. 提交前先将代码提交到暂缓区
4. 提交到本地仓库，本地仓库保存修改的各个历史版本
5. 修改完成后，需要和团队成员共享代码时，将代码推送到远程仓库

即 克隆-》创建分支-》添加-》提交-》推送

#### git底层原理

​	git切换版本，底层其实是移动的HEAD指针，类似于双向链表

## git常用命令

#### 环境配置

| 命令名称                             | 作用                                   |
| ------------------------------------ | -------------------------------------- |
| git config --global user.name 用户名 | 设置用户签名                           |
| git config --global user.email 邮箱  | 设置用户签名                           |
| git init                             | 初始化本地库(在哪里打开那里就是本地库) |
| git config --list                    | 查看配置信息                           |
| git config user.name                 | 查看用户名(其他同理)                   |

签名的作用：区分不同操作者身份，确认知道是谁提交的

#### 	基础命令

| 命令名称                           | 作用                               |
| ---------------------------------- | ---------------------------------- |
| git status                         | 查看本地库状态                     |
| git status -s                      | 输出信息更简洁                     |
| git add 文件名                     | 添加到暂存区                       |
| git commit -m "日志信息" 文件名    | 提交到本地库                       |
| git commit -a -m "日志信息 文件名" | 添加并提交(只适用修改过后的文件)   |
| git reset 文件名                   | 取消缓冲区文件                     |
| git rm 文件名                      | 删除本地文件，然后需要提交到本地库 |
| git reflog                         | 查看操作日志                       |
| git log                            | 查看详细操作日志                   |
| git log --oneline                  | 一行显示                           |
| git reset --hard 版本号            | 版本穿梭                           |
| git reset --hard HEAD^             | 回退一个版本(多个^回退多个)        |

**查询状态时的特点：**

````tst
红色(??)：untracked 未跟踪（未被纳入版本控制）
绿色(A)：Staged 已暂存状态(已经被添加到了暂存区)
tracked 已跟踪（被纳入版本控制）
Unmodified 未修改状态
Modified 已修改状态
版本相关
版本号：黄色的字符数字混合体，用于版本穿梭
即使用reflog查看的日志
````

#### 分支操作

**什么是分支**
	分支类似与创建了一个副本，该分支的修改不会对主分支造成影响
**分支的好处**
	同时并行推进多个功能开发，提高开发效率
	各个分支在开发过程中，如果某一个分支开发失败，不会对其他分支有任何影响，失败的分支删除重新开始即可。
**分支操作**

| 命令名称               | 作用                                                         |
| ---------------------- | ------------------------------------------------------------ |
| git branch 分支名      | 创建分支                                                     |
| git branch -v          | 列出所有本地分支                                             |
| git branch -a          | 列出所有本地分支和远程分支                                   |
| git branch -r          | 列出所有远程分支                                             |
| git checkout 分支名    | 切换分支                                                     |
| git merge 分支名       | 把指定的分支合并到当前分支上                                 |
| git branch -d 分支名   | 删除分支(如果要删除的分支中进行了一些开发动作，比如有文件未合并，此时不会删除分支，-D表示坚持删除) |
| git checkout -b 分支名 | 创建并切换到新分支                                           |

**分支相关解释**

````tst
*：当前分支
没有*：其他分支
MERGING:合并分支产生了冲突
````

	通过查看文件看出git进行了提示
	<<<<<<< HEAD
	hello git! hello atguigu! master test
	hello git! hello atguigu!
	=======
	hello git! hello atguigu!
	hello git! hello atguigu! hot-fix test
	>>>>>>> hot-fix
	特殊符号：<<<<<<< HEAD 当前分支的代码 ======= 合并过来的代码 >>>>>>> hot-fix
	原因：合并分支时，两个分支在同一个文件的同一个位置有两套完全不同的修改。
	解决方法：编辑有冲突的文件，删除特殊符号，决定要使用的内容
	然后添加到暂存箱，提交到本地库(不能带文件名!!)
#### Git标签

| 命令                                     | 说明                 |
| ---------------------------------------- | -------------------- |
| git tag 标签名 标签                      | 创建新标签           |
| git tag                                  | 查看所有标签         |
| git push xxx 标签名                      | 将标签推送至远程仓库 |
| git tag -d [标签]                        | 删除本地标签         |
| git push 远程库地址别名:refs/tags/[标签] | 删除远程标签         |

## 远程仓库操作

#### 基本命令

| 命令                               | 作用                                                         |
| ---------------------------------- | ------------------------------------------------------------ |
| git remote -v                      | 查看当前所有远程地址别名                                     |
| git remote add 别名 远程地址       | 给远程仓库起别名                                             |
| git push 别名 分支                 | 推送本地分支上的内容到远程仓库                               |
| git clone 远程地址                 | 将远程仓库的内容克隆到本地                                   |
| git pull 远程库地址别名 远程分支名 | 将远程仓库的分支最新内容拉下来后与当前本地分支直接合并       |
| git remote rm 别名                 | 移除远程仓库(此命令只是从本地移除远程仓库的记录，并不会真正影响到远程仓库) |

	小结：远程地址来源于github中创建的仓库，clone 会做如下操作。1、拉取代码。2、初始化本地仓库。3、创建别名

#### 邀请加入团队

#### 跨团队协助

#### 小Demo

#### SSH公匙

````tex
--进入当前用户的家目录
Layne@LAPTOP-Layne MINGW64 /d/Git-Space/SH0720 (master)
$ cd
--删除.ssh 目录
Layne@LAPTOP-Layne MINGW64 ~
$ rm -rvf .ssh
removed '.ssh/known_hosts'
removed directory '.ssh'
--运行命令生成.ssh 秘钥目录[注意：这里-C 这个参数是大写的 C]
Layne@LAPTOP-Layne MINGW64 ~
$ ssh-keygen -t rsa -C atguiguyueyue@aliyun.com
Generating public/private rsa key pair.
Enter file in which to save the key (/c/Users/Layne/.ssh/id_rsa):
Created directory '/c/Users/Layne/.ssh'.
Enter passphrase (empty for no passphrase):
Enter same passphrase again:
Your identification has been saved in /c/Users/Layne/.ssh/id_rsa.
Your public key has been saved in /c/Users/Layne/.ssh/id_rsa.pub.
The key fingerprint is:
SHA256:7CPfRLITKcYDhaqpEDeok7Atvwh2reRmpxxOC6dkY44 
atguiguyueyue@aliyun.com
The key's randomart image is:
+---[RSA 2048]----+
| .. |
| .. |
| . .. |
|+ + o . . |
|oO . = S . |
|X . .. + = |
|+@ * .. = . |
|X.&o+. o = |
|Eo+Oo . . |
+----[SHA256]-----+
--进入.ssh 目录查看文件列表
Layne@LAPTOP-Layne MINGW64 ~
$ cd .ssh
Layne@LAPTOP-Layne MINGW64 ~/.ssh
$ ll -a
total 21
drwxr-xr-x 1 Layne 197609 0 11 月 25 19:27 ./
drwxr-xr-x 1 Layne 197609 0 11 月 25 19:27 ../
-rw-r--r-- 1 Layne 197609 1679 11 月 25 19:27 id_rsa
-rw-r--r-- 1 Layne 197609 406 11 月 25 19:27 id_rsa.pub
--查看 id_rsa.pub 文件内容
Layne@LAPTOP-Layne MINGW64 ~/.ssh
$ cat id_rsa.pub
ssh-rsa 
AAAAB3NzaC1yc2EAAAADAQABAAABAQDRXRsk9Ohtg1AXLltsuNRAGBsx3ypE1O1Rkdzpm
l1woa6y6G62lZri3XtCH0F7GQvnMvQtPISJFXXWo+jFHZmqYQa/6kOIMv2sszcoj2Qtwl
lGXTPn/4T2h/cHjSHfc+ks8OYP7OWOOefpOCbYY/7DWYrl89k7nQlfd+A1FV/vQmcsa1L
P5ihqjpjms2CoUUen8kZHbjwHBAHQHWRE+Vc371MG/dwINvCi8n7ibI86o2k0dW0+8SL+
svPV/Y0G9m+RAqgec8b9U6DcSSAMH5uq4UWfnAcUNagb/aJQLytrH0pLa8nMv3XdSGNNo
AGBFeW2+K81XrmkP27FrLI6lDef atguiguyueyue@aliyun.com
````

复制 id_rsa.pub 文件内容，登录 GitHub，点击用户头像→Settings→SSH and GPG keys

接下来再往远程仓库 push 东西的时候使用 SSH 连接就不需要登录了。

## IDEA集成Git

