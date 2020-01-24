## About

This app allows you to load data from xml or csv files to database.

## Start

Download parser_jar.
Change properties files according your system configuration and goals.
Run start.bat.

## Description

Properties file app.properties example

```properties
version=1.0
#Avaible values: mysql, msserver
db_type=mysql
db_driver=com.mysql.cj.jdbc.Driver
db_url=jdbc:mysql://localhost:3306/MyDB
user=usr
password=psw
#Path to XML files
xml_path=D:/InputXMLData
#Parse to csv?(true or false)
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

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)
