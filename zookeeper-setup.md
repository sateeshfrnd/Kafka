# Install/Setup Zookeeper

`Zookeeper` can be installed on Ubuntu either using **apt-get** or **binary file (.tar)**


# Install using apt-get
Since the ZooKeeper package is available in Ubuntu's default repositories, install it using apt-get.
```
$ sudo apt-get install zookeeperd
```
After the installation completes, ZooKeeper will be started as a daemon automatically. By default, it will listen on **port 2181**.

To make sure that it is working, connect to it via Telnet:
```
$ telnet localhost 2181
```
At the Telnet prompt, type in ruok and press ENTER.
If everything's fine, ZooKeeper will say imok and end the Telnet session.

# Install using binary file
- Download ZooKeeper Binaries [zookeeper-3.4.6](http://mirror.nus.edu.sg/apache/zookeeper/zookeeper-3.4.6/zookeeper-3.4.6.tar.gz)
- Unzip the downloaded file and put the unzipped folder in a folder, say the following
  ```opt/ZooKeeper/zookeeper-3.4.6```
- Set Classpath
	1. Move to `$HOME` directory and run the following command to open the `.bashrc` file in terminal:
	   ```$ gedit .bashrc```
	
	2. Add the following line to the end:
           ```export ZK_HOME=opt/ZooKeeper/zookeeper-3.4.6```
	
	3. Save and close the .bashrc file, run the following command to update the environment var: 
	   ```source .bashrc```

- Configuration
	1. Move to the $ZK_HOME/conf directory
		```
		cd $ZK_HOME/conf
		```
	2. Run the following command to create the configuration zoo.cfg
	   ```
		touch zoo.cfg  
		gedit zoo.cfg
	   ```
	3. Open zoo.cfg, add the following lines and save.
	   ```
	   tickTime=2000  
	   dataDir=/tmp/zookeeper  
	   clientPort=2181
	   ```
          
- Start zookeeper
	- Run the following command to start the zookeeper on the machine:
	  ```$bin/zkServer.sh start```
	- To make sure that it is working,check the java process running:
	  ```$jps```
		
- ZooKeeper Status
	- Run the following command to check the status of zookeeper:  
	  ```$bin/zkServer.sh status```

- Stop ZooKeeper
	- Run the following command to stop the zookeeper:  
	  ``` $bin/zkServer.sh stop ```
	

	
	
