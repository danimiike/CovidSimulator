# CovidSimulator
This will be our project repository. In here I will updade the README file with our last update.  
## How to use GIT
Firstly, make sure that you have git ion your computer. Follow the instructions:
1. Open your PowerShell/CommandLine/Terminal and run  
  `git --version`
  If something show up displaying the version you have git installed. GOOD!
2. If you do not have git in your pc you can installed by download and install using this link:  
  [Git official website](https://git-scm.com/downloads).  
Now lets work by connect this repository and our poroject together. Each one of you will have a different setups.
### Setup For Dani
Dani, I will write in english in case Georgia already has the project on her computer she can follow this step.  
1. Open the project folder.
2. And at the "url" bar of the folder type `cmd`
3. with the Command Pronpt or Command Line opened we need to initialize the git repo. Type:  
  ```git init```
4. After initialize your a git for this project you will have to connect to an existed repo, which is this one. Type:  
  ```git remote add origin https://github.com/FelipeLLeite/CovidSimulator.git```  
  To make sure that the conect is correct run: `git remote show origin` you should see the following information.  
  ```
  * remote origin
  Fetch URL: https://github.com/FelipeLLeite/CovidSimulator.git
  Push  URL: https://github.com/FelipeLLeite/CovidSimulator.git
  HEAD branch: master
  Remote branch:
    master new (next fetch will store in remotes/origin)
  ```
  This show that your are connect to the reposoiitory and the HEAD branch, the main project, is located in the branch called master.
5. Lastly, run the following commands ONE BY ONE. If pronpt for you to enter your Username and Password, this should be you credentials of your github.  
  ```
  git add *
  git commit -m "Dani's first commit"
  git push -u origin master
  git push
  ```
Now Dani is all set. Every time you open your code you MUST open the `cmd` from your project folder and run `git pull`. This will garantee that you always have the lastest update from the team.  
### Setup For Georgia
For Geaorgia it is easier than Dani's setup. All you have to do is open a folder where you want the project be located. Open the `cmd` (command pronpt or command line) from that folder and run the following command:  
  ```git clone https://github.com/FelipeLLeite/CovidSimulator.git```  
This will clone the project to your pc witht he latest version. The project will be located at the folder you open the `cmd`. If you want to save your project in another foler or path just add the folder name or path string to the end of the last command as additional parameter. Example:  
  ```git clone https://github.com/FelipeLLeite/CovidSimulator.git CovidSimulator```  
You are all set.  Every time you open your code you MUST open the `cmd` from your project folder and run `git pull`. This will garantee that you always have the lastest update from the team.  
## Update our repo
Whenever you finish your daily work with our project you MUST run the following git command at the `cmd` of the project folder  
```git commit -am "Please, type here YOUR small simple text saying what changes you did"```  
After that you need to push  
```git push```  
Congratulation! You are now working with GIT (VCS - Version Control System). I will teach more commands in the future.  
