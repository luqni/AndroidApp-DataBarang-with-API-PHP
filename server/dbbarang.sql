CREATE TABLE IF NOT EXISTS `tbl_barang` (
`id` int(4) NOT NULL,
`kode` varchar(8) NOT NULL,
`nama` varchar(32) NOT NULL,
`harga` int(12) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_barang`
--

INSERT INTO `tbl_barang` (`id`, `kode`, `nama`, `harga`) VALUES
(1, 'B001', 'Mouse', 50000),
(2, 'B002', 'Keyboard', 70000),
(3, 'B006', 'Kopi', 6000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_barang`
--
ALTER TABLE `tbl_barang`
ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_barang`
--
ALTER TABLE `tbl_barang`
MODIFY `id` int(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
