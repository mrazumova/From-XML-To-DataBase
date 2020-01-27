## About

This app allows you to load data from xml or csv files to database.

Available databases: MySQL, MS SQL Server.

## Start

Download parser_jar.

Change file app.properties according your system configuration and goals.

Run start.bat.

## Configurations

Properties file app .properties example:

```properties
version=1.0
#Available values: mysql, msserver
db_type=mysql
#Connection settings
db_driver=com.mysql.cj.jdbc.Driver
db_url=jdbc:mysql://localhost:3306/MyDB
user=usr
password=psw
#Path to XML files
xml_path=D:/InputXMLData
#Parse to csv?(true or false)
#!If you have files only in xml format then you should parse them into csv files. 
parse_to_csv=true
#Path to CSV files
csv_path=D:/InputCSVData
#Files names separated by ';'(should be the same as table names in database)
files=file1;file2;file3
#Upload to database?(true or false)
load_to_db=false
#Seprator for fields(data shouldn`t contain this character)
separator=~
```

In log4j.properties you should only path to log file.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License

[MIT](https://choosealicense.com/licenses/mit/)