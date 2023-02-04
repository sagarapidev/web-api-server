# web-api-server
 
###### How to run MySQL Server using Bin Lib ?
{
"name":"MySql",
"note":"Set bin path in System Environment for run (using binary download)",
"Steps":{
"Step-1":"E:\Database\mysql8\bin>mysqld --console --initialize --basedir=E:\Database\mysql8 --datadir=E:\Database\mysqldata",
"Step-2""copy temporary password(Eg:(:!Ne8+c+K.<y))",
"Step-3":"E:\Database\mysql8\bin>mysqld --console --datadir=E:\Database\mysqldata",
"Step-4":"Open another command prompt",
"Step-5":"mysql -u root -p",
"Step-6":"Enter temporary password",
"Step-7":"alter user 'root'@'localhost' identified by 'rootpass'",
"Step-8":"Close login with new password",
"Step-9":"CREATE USER 'admin'@'localhost' IDENTIFIED BY 'Adminpwd@5345'",
"Step-10":"GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost' WITH GRANT OPTION",
"Step-11":"FLUSH PRIVILEGES;"
}

}