ECHO OFF
cd ..
javac -d classes src/core/util/*.java src/core/persistent/*.java src/core/db/*.java src/core/db/entity/*.java src/core/db/dao/*.java src/core/commands/*.java src/core/commands/listedArguments/*.java src/core/commands/mappedArguments/*.java src/core/commands/noArguments/*.java src/dbApp/tablesManager/*.java src/apps/*.java
