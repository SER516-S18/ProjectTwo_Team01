Contributers :

Chetanya Ahuja || 
Cephas Armstrong-Mensah  || 
Shibani Arvind Hegde  || 
Nikita Bahl  || 
Sanchari Banerjee  || 
Jahnavi Bantupalli  || 
Zelin Bao  || 
Pratik Bartakke  || 
Karansher Bhangal  || 
Kritika Bhat  || 
Shilpa Bhat  || 
Vihar Bhatt  || 
Debarati Bhattacharyya

Running Project Two - Team 01 Client - Server Application

Use the below command to compile all the classes and resolve the dependencies:

javac -cp “.:./lib/jcommon-1.0.23.jar:./lib/jfreechart-1.0.19.jar:./lib/jgoodies-forms-1.4.0.jar:./lib/json.jar” util/commons/*java client/client/gui/*.java client/client/sys/*.java  server/server/gui/*java server/server/sys/*java server/org/eclipse/wb/swing/*java

Once compiled, issue the below command for the Client:

java -cp "./server/:./client/:./util/:./util/commons:./lib/jcommon-1.0.23.jar:./lib/jfreechart-1.0.19.jar:./lib/jgoodies-forms-1.4.0.jar:./lib/json.jar" client.gui.ClientMainWindow

Once compiled, issue the below command for the Server:
