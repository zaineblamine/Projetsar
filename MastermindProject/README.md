#ce projet est réalisé par Lamine zaineb
#IF4 groupe B02
#Année Universitaire 2015-2016
#version du jdk utilisé 1.8.0_65


#compilation du projet
cd Mastermind/client
javac ICallback.java
javac Callback.java
rmic -v1.1 Callback

cd ../server
cp ../client/ICallback.class .
cp ../client/Callback_stub.class .
javac Servant.java
javac Game.java
javac GameImp.java
javac FabGame.java
javac FabGameImp.java
rmic -v1.1 GameImp
rmic -v1.1 FabGameImp
javac *.java
java -Djava

cd ../client
cp ../server/Game.class
cp ../server/GameImp_Stub.class .
cp ../server/FabGame.class .
cp ../server/FabGameImp_Stub.class .
javac *.java


cd ../www
on Déplace manuellement ou en utilisant la commande « mv » tous les fichiers du
dossier « server » au dossier «www » sauf les fichiers suivants :
- DynamicGameServer.java, DynamicGameServer.class, Server.policy

Déplacer manuellement ou en utilisant la commande « mv » tous les fichiers du
dossier « client » au dossier «www » sauf les fichiers suivants :
- DynamicGameClient.java, DynamicGameClient.class, Client.policy

#execution du projet
cd ../server
java -Djava.security.policy=server.policy -Djava.rmi.server.codebase=file:///home/zaineb/Desktop/Mastermind/www/ DynamicGameServer

cd ../client
java -Djava.security.policy=client.policy -Djava.rmi.server.codebase=file:///home/zaineb/Desktop/Mastermind/www/ DynamicGameClient

