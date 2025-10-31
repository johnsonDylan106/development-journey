<!DOCTYPE html>
<html lang="en">
<head>
 <meta charset="utf-8">
<!-- deleteData.php - Demonstrate deleting data using SQL 
  Student Name
  Written: Current Date
  Revised:   
  -->
 <title>Delete Data</title>
<link rel="stylesheet" type="text/css" href="registration.css">


</head>
<body>
<div id="frame">
   
   <h1>Delete Data</h1>

<?PHP
   // Set up connection constants
   // Using default username and password for AMPPS  
   define("SERVER_NAME",   "localhost");
   define("DBF_USER_NAME", "root");
   define("DBF_PASSWORD",  "mysql");
   define("DATABASE_NAME", "sunRun");
   // Global connection object
   $conn = NULL;

   // Link to external library file
   //echo "PATH (Current Working Directory): " . getcwd( ) . "sunRunLib.php" . "<br />";
   require_once(getcwd( ) . "/sunRunLib.php");   
   // Connect to database
   createConnection();

   /* 
   // Quickly empty a table using TRUNCATE
   $sql = "TRUNCATE TABLE runner";
   $result = $conn->query($sql);  
   echo "<p>Table: runner is now empty.</p>";
   /* */ 

   /*
   //DROP a table
   $sql = "DROP TABLE runner";
   $result = $conn->query($sql);
   echo "<p>Table: runner is gone, gone, gone.</p>";
   /* */

   /* 
   // DROP an entire database
   $sql = "DROP DATABASE sunRun";
   $result = $conn->query($sql);  
   echo "<p>sunRun has been completely deleted.</p>";
   /* */ 

?>
   <p>
      <strong>Need to rebuild the database and tables?</strong> Run sunRunCreate.php.
   </p>   
</div> <!-- end of #frame -->
</body>
</html>