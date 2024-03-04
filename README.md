# Movie Booking System
## About
This project is about developing a Movie Ticket Booking Management System using Java. This project is built through using different agile tools which allows all team members to contribute. Before using the software, please ensure that you have opened an account in our customer portal and have a valid gift card or credit card to book movies with. Additionally, ensure that you have a staff account or manager account if you wish to obtain admin privileges. If you don't have an account, you may also register one using our system. 

## Installation
*Note: If you have installed java and gradle on the computer, please feel free to jump to project instruction and start experiencing our new software.*

**For users who have neither java nor gradle installed:**
1. Please go to https://java.com/en/download/help/download_options.html and install java based on your operating system by following the instructions.
2. Please go to https://www.jetbrains.com/idea/download/#section=mac and install the java IDE based on your operating system by following the instructions
3. Please go to https://gradle.org/install/ and install gradle based on your operating system by following the instructions.

**For users who have java installed but not gradle:**
1. Please goto https://gradle.org/install/ and install gradle based on your operating system.

## Basic command for Gradle
* `gradle init` will automatically start a new project from scratch.
* `gradle run` will automatically run and build the project.
* `gradle test` will automatically test the project and generate a jacoco test report.
* `gradle clean build` will automatically clear all the build directory (that contains all previous built files).

## Basic command for GitHub
* `git pull` for fetch and merging any new changes from the remote branches to the local branches.
* `git add` for adding new file
* `git commit` for committing new changes to the branch
* `git push <branch_name>` for pushing the new changes to the remote branch
* `git branch <branch_name>` for creating a new branch
* `git checkout <branch_name>` for switching to the <branch_name>
* `git merge <branch_name>` for merging the work in <branch_name> to the current branch

# Project Instruction
## Run the project
1. Clone the repo
```
git clone https://github.com/WinnieJ20/Movie-booking-system.git
```
2. Run the application
```
gradle run --console=plain
```
3. View sessions available for movies across location and screen size
4. Choose the desired session and press book
5. Follow the instructions to login as a customer or register for an account as a guest 
6. Select seats in the session chosen
7. Pay for tickets
8. Enjoy the movie

## Test the project
1. `gradle test` in the command line
