# task-tracker-1
Task Tracker project

<h3>By Zeus Añora</h3>

<p>The project is a CLI task tracker that acts as a task manager for day-to-day tasks. This project makes use of command line arguments and json external libraries to function</p>

<p>This is project is not for commercial use, this project can be used and shared by anyone</p>

<p>This project uses the following modules during development:</p>
<ul>
  <li>Java</li>
  <li>JSON</li>
  <li>Windows PowerShell (Or other command line terminal)</li>
</ul>

<br>

# Project Usage
<p>To use the project, just clone the main branch into a git initialized directory.</p>
<p>Head to the directory of the project then input the following command below:</p>
<h3>javac -cp "jar/*" *.java</h3>
<p>This command compiles the external JSON dependencies into the class path</p>
<br>
<p>We then use the command below:</p>
<h3>java -cp ".;jar/*" Main `method` `parameter` </h3>
<p>Inside `method` is the method of the project while inside `parameter` is the parameter that the method may need</p>

<br>

# Project Methods
<h3>To add task/s</h3>
<ul>
  <li>java -cp ".;jar/*" Main add <p>Method to add a task</p></li>
</ul>
<br>
<h3>To update task/s</h3>
<ul>
  <li>java -cp ".;jar/*" Main update `id` <p>Method to update a task (input the id of the task in `id`)</p></li>
</ul>
<br>
<h3>To delete task/s</h3>
<ul>
  <li>java -cp ".;jar/*" Main delete `id` <p>Method to delete a task (input the id of the task in `id`)</p></li><br>
  <li>java -cp ".;jar/*" Main delete all <p>Method to delete all tasks</p></li>
</ul>
<br>
<h3>To show task/s</h3>
<ul>
  <li>java -cp ".;jar/*" Main show todo <p>Method to show tasks with "Todo" status</p></li><br>
  <li>java -cp ".;jar/*" Main show inprogress <p>Method to show tasks with "In-progress" status</p></li><br>
  <li>java -cp ".;jar/*" Main show done <p>Method to show tasks with "Done" status</p></li><br>
  <li>java -cp ".;jar/*" Main show all <p>Method to show all tasks</p></li>
</ul>

<br><br>
<p>Happy Coding :)</p>
