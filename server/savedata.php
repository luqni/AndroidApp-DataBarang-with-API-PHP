<?php
	include 'dbconfig.php';

	$nama = $_POST['nama'];
	$kode = $_POST['kode'];
	$harga= $_POST['harga'];
	$id = $_POST['id'];

//membuat koneksi
$conn = mysqli_connect($servername, $username, $password, $dbname);
//cek koneksi
if (!$conn) {

	    die("Connection failed: " . mysqli_connect_error());
	}
$query = "insert into tbl_barang (kode, nama, harga) values('".$kode."','".$nama."','".$harga."')";
if ($id != "0"){
$query = "update tbl_barang set nama='".$nama."',kode='".$kode."',harga='".$harga."' where id=".$id;
}
$response = mysqli_query($conn, $query) or die('Error query:  '.$query);
$lid = mysqli_insert_id($conn);
$result["errormsg"]="Success";
$result["lid"]="$lid";
if ($response == 1){
	    echo json_encode($result);
}
else{
    $result["errormsg"]="Fail";
    echo json_encode($result);
}
?>
