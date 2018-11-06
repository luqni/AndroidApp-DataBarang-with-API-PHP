<?php
include 'dbconfig.php';
//membuat koneksi
$conn = mysqli_connect($servername, $username, $password, $dbname);
//cek koneksi
if (!$conn) {
	die("Connection failed:" . mysqli_connect_error());
}
$result["errorcode"]="0";
$sql = "SELECT id, kode, nama, harga FROM tbl_barang order by kode";
$res = mysqli_query($conn,$sql);
$items = array();
if (mysqli_num_rows($res) > 0) {

	while($row = mysqli_fetch_object($res)){
		array_push($items, $row);
	}
$result["errorcode"] = "1";
	$result["data"] = $items;
}else{
	$result["errormsg"] = "Tidak ada data";
}
echo json_encode($result);
mysqli_close($conn);
?>


?>
