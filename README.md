# Contributers:
>
- Chetanya Ahuja  
- Cephas Armstrong-Mensah   
- Shibani Arvind Hegde   
- Nikita Bahl   
- Sanchari Banerjee   
- Jahnavi Bantupalli   
- Zelin Bao   
- Pratik Bartakke   
- Karansher Bhangal   
- Kritika Bhat   
- Shilpa Bhat   
- Vihar Bhatt   
- Debarati Bhattacharyya

>
## Running Project Two - Team 01 Client and Server Application
### Use the below command to compile all the classes and resolve the dependencies, must be in the Project Folder:
>

*Windows Machine:*
```
javac -cp ".;./lib/jcommon-1.0.23.jar;./lib/jfreechart-1.0.19.jar;./lib/jgoodies-forms-1.4.0.jar;./lib/json.jar" util/*.java client/gui/*.java client/sys/*.java  server/gui/*.java server/sys/*.java org/eclipse/wb/swing/*.java
```
*Linux or Mac OSx:*
```
javac -cp ".:./lib/jcommon-1.0.23.jar:./lib/jfreechart-1.0.19.jar:./lib/jgoodies-forms-1.4.0.jar:./lib/json.jar" util/*.java client/gui/*.java client/sys/*.java  server/gui/*.java server/sys/*.java org/eclipse/wb/swing/*.java
```
>
### Once compiled, issue the below command for the Client:
>

*Windows Machine:*
```
java -cp ".;./server;./client;./util;./lib/jcommon-1.0.23.jar;./lib/jfreechart-1.0.19.jar;./lib/jgoodies-forms-1.4.0.jar;./lib/json.jar" client.gui.ClientMainWindow
```

*Linux or Mac OSx:*
```
java -cp ".:./server:./client:./util:./lib/jcommon-1.0.23.jar:./lib/jfreechart-1.0.19.jar:./lib/jgoodies-forms-1.4.0.jar:./lib/json.jar" client.gui.ClientMainWindow
```
>
### Once compiled, issue the below command for the Server:
>

*Windows Machine:*
```
java -cp ".;./server;./client;./util;./lib/jcommon-1.0.23.jar;./lib/jfreechart-1.0.19.jar;./lib/jgoodies-forms-1.4.0.jar;./lib/json.jar" server.gui.ServerMainWindow
```

*Linux or Mac OSx:*
```
java -cp ".:./server:./client:./util:./lib/jcommon-1.0.23.jar:./lib/jfreechart-1.0.19.jar:./lib/jgoodies-forms-1.4.0.jar:./lib/json.jar" server.gui.ServerMainWindow
```
