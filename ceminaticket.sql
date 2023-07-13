<<<<<<< HEAD
﻿USE [master]
GO
/****** Object:  Database [Cinema_Ticket]    Script Date: 6/29/2023 2:46:05 AM ******/
CREATE DATABASE [Cinema_Ticket]
GO
ALTER DATABASE [Cinema_Ticket] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET ARITHABORT OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Cinema_Ticket] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Cinema_Ticket] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Cinema_Ticket] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Cinema_Ticket] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET RECOVERY FULL 
GO
ALTER DATABASE [Cinema_Ticket] SET  MULTI_USER 
GO
ALTER DATABASE [Cinema_Ticket] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Cinema_Ticket] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Cinema_Ticket] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Cinema_Ticket] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Cinema_Ticket] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Cinema_Ticket', N'ON'
GO
ALTER DATABASE [Cinema_Ticket] SET QUERY_STORE = ON
GO
ALTER DATABASE [Cinema_Ticket] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [Cinema_Ticket]
GO
/****** Object:  Table [dbo].[ChiTietThanhToan]    Script Date: 6/29/2023 2:46:05 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietThanhToan](
	[id_hoa_don] [uniqueidentifier] NULL,
	[id_phuong_thuc_thanh_toan] [uniqueidentifier] NULL,
	[tong_tien] [money] DEFAULT 0,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChucVu](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](100) NULL,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Combo]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Combo](
	[id] [uniqueidentifier] NOT NULL,
	[ten_combo] [nvarchar](255) NULL,
	[gia] [money] DEFAULT 0,
	[deleted] [bit] DEFAULT 0 ,
	[update_by] [nvarchar](255) NULL,
	[update_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[create_at] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ComboDoAnDetail]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ComboDoAnDetail](
	[id_combo] [uniqueidentifier] NOT NULL,
	[id_do_an] [uniqueidentifier] NOT NULL,
	[deleted] [bit] DEFAULT 0 ,
	[so_luong] [int] DEFAULT 0,
	[create_at] [datetime] NULL,
	[update_by] [uniqueidentifier] NULL,
	[update_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_combo] ASC,
	[id_do_an] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DoAn]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DoAn](
	[id] [uniqueidentifier] NOT NULL,
	[Ten] [nvarchar](255) NULL,
	[Gia] [money] DEFAULT 0,
	[create_at] [datetime] NULL,
	[deleted] [bit] DEFAULT 0 ,
	[update_by] [uniqueidentifier] NULL,
	[update_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ghe]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ghe](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](10) NULL,
	[id_loai_ghe] [uniqueidentifier] NULL,
	[id_phong_chieu] [uniqueidentifier] NULL,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GiaVeLichChieu]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GiaVeLichChieu](
	[id_lich_chieu] [uniqueidentifier] NOT NULL,
	[id_loai_ghe] [uniqueidentifier] NOT NULL,
	[gia] [money] DEFAULT 0,
	[so_luong_ghe] [int] DEFAULT 0,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id_lich_chieu] ASC,
	[id_loai_ghe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[id] [uniqueidentifier] NOT NULL,
	[id_nhan_vien] [uniqueidentifier] NULL,
	[id_khach_hang] [uniqueidentifier] NULL,
	[ghi_chu] [nvarchar](max) NULL,
	[tong_gia] [money] DEFAULT 0,
	[tong_gia_sau_giam] [money] DEFAULT 0,
	[trang_thai] [int] DEFAULT 0,
	[thoi_gian_thanh_toan] [datetime] NULL,
	[create_at] [datetime] NULL,
	 [update_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonDoAn]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonDoAn](
	[id_hoa_don] [uniqueidentifier] NOT NULL,
	[id_combo] [uniqueidentifier] NOT NULL,
	[soluong] [int] DEFAULT 0,
	[gia] [money] DEFAULT 0,
	[deleted] [bit] DEFAULT 0 ,
	[update_by] [uniqueidentifier] NULL,
	[update_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[create_at] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_hoa_don] ASC,
	[id_combo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[id] [uniqueidentifier] NOT NULL,
	[ho_ten] [nvarchar](100) NULL,
	[email] [nvarchar](200) NULL,
	  [mat_khau] [varchar](20) NULL,
	[gioi_tinh] [bit] NULL,
	[ngay_sinh] [date] NULL,
	[so_dien_thoai] [nvarchar](50) NULL,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LichChieu]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LichChieu](
	[id] [uniqueidentifier] NOT NULL,
	[id_phim] [uniqueidentifier] NULL,
	[gio_chieu] [time](7) NULL,
	[gio_ket_thuc] [time](7) NULL,
	[ngay_chieu] [time](7) NULL,
	[id_phong_chieu] [uniqueidentifier] NULL,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiGhe]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiGhe](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](20) NULL,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NgonNgu]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NgonNgu](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](20) NULL,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[id] [uniqueidentifier] NOT NULL,
	[id_chuc_vu] [uniqueidentifier] NULL,
	[ho_ten] [nvarchar](100) NULL,
	 [mat_khau] [varchar](20) NULL,
	[email] [nvarchar](100) NULL,
	[so_dien_thoai] [nvarchar](10) NULL,
	[image_nv] [nvarchar](max) NULL,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhongChieu]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhongChieu](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](20) NULL,
	[so_luong_ghe] [int] DEFAULT 0,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhuongThucThanhToan]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhuongThucThanhToan](
	[id] [uniqueidentifier] NOT NULL,
	[hinh_thuc_thanh_toan] [nvarchar](100) NULL,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QuocGia]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuocGia](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](20) NULL,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TheLoai]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TheLoai](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](100) NULL,
	[mo_ta] [nvarchar](255) NULL,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TheLoaiPhim]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TheLoaiPhim](
	[id_film] [uniqueidentifier] NOT NULL,
	[id_the_loai] [uniqueidentifier] NOT NULL,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id_film] ASC,
	[id_the_loai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ThongTinPhim]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThongTinPhim](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](20) NULL,
	[dao_dien] [nvarchar](20) NULL,
	[nha_san_xuat] [nvarchar](20) NULL,
	[dien_vien] [nvarchar](20) NULL,
	[nam_phat_hanh] [int] DEFAULT 0,
	[thoi_luong] [int] DEFAULT 0,
	[tuoi_gioi_han] [int] DEFAULT 0,
	[id_quoc_gia] [uniqueidentifier] NULL,
	[id_ngon_ngu] [uniqueidentifier] NULL,
	-- [ngay_khoi_chieu] [date] NULL,
	[noidung] [nvarchar](max) NULL,
	[trailer] [nvarchar](max) NULL,
	[poster] [nvarchar](max) NULL,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ve]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ve](
	[id_hoa_don] [uniqueidentifier] NULL,
	[id_lich_chieu] [uniqueidentifier] NOT NULL,
	[id_ghe] [uniqueidentifier] NOT NULL,
	[gia] [money] DEFAULT 0,
	[ngay_dat_ve] [date] NULL,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id_lich_chieu] ASC,
	[id_ghe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChucVu] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[Combo] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[DoAn] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[Ghe] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[KhachHang] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[LichChieu] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[LoaiGhe] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[NgonNgu] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[PhongChieu] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[PhuongThucThanhToan] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[QuocGia] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[TheLoai] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[ThongTinPhim] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[ChiTietThanhToan]  WITH CHECK ADD FOREIGN KEY([id_hoa_don])
REFERENCES [dbo].[HoaDon] ([id])
GO
ALTER TABLE [dbo].[ChiTietThanhToan]  WITH CHECK ADD FOREIGN KEY([id_phuong_thuc_thanh_toan])
REFERENCES [dbo].[PhuongThucThanhToan] ([id])
GO
ALTER TABLE [dbo].[ComboDoAnDetail]  WITH CHECK ADD FOREIGN KEY([id_combo])
REFERENCES [dbo].[Combo] ([id])
GO
ALTER TABLE [dbo].[ComboDoAnDetail]  WITH CHECK ADD FOREIGN KEY([id_do_an])
REFERENCES [dbo].[DoAn] ([id])
GO
ALTER TABLE [dbo].[Ghe]  WITH CHECK ADD FOREIGN KEY([id_loai_ghe])
REFERENCES [dbo].[LoaiGhe] ([id])
GO
ALTER TABLE [dbo].[Ghe]  WITH CHECK ADD FOREIGN KEY([id_phong_chieu])
REFERENCES [dbo].[PhongChieu] ([id])
GO
ALTER TABLE [dbo].[GiaVeLichChieu]  WITH CHECK ADD FOREIGN KEY([id_lich_chieu])
REFERENCES [dbo].[LichChieu] ([id])
GO
ALTER TABLE [dbo].[GiaVeLichChieu]  WITH CHECK ADD FOREIGN KEY([id_loai_ghe])
REFERENCES [dbo].[LoaiGhe] ([id])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([id_khach_hang])
REFERENCES [dbo].[KhachHang] ([id])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([id_nhan_vien])
REFERENCES [dbo].[NhanVien] ([id])
GO
ALTER TABLE [dbo].[HoaDonDoAn]  WITH CHECK ADD FOREIGN KEY([id_combo])
REFERENCES [dbo].[Combo] ([id])
GO
ALTER TABLE [dbo].[HoaDonDoAn]  WITH CHECK ADD FOREIGN KEY([id_hoa_don])
REFERENCES [dbo].[HoaDon] ([id])
GO
ALTER TABLE [dbo].[LichChieu]  WITH CHECK ADD FOREIGN KEY([id_phim])
REFERENCES [dbo].[ThongTinPhim] ([id])
GO
ALTER TABLE [dbo].[LichChieu]  WITH CHECK ADD FOREIGN KEY([id_phong_chieu])
REFERENCES [dbo].[PhongChieu] ([id])
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD FOREIGN KEY([id_chuc_vu])
REFERENCES [dbo].[ChucVu] ([id])
GO
ALTER TABLE [dbo].[TheLoaiPhim]  WITH CHECK ADD FOREIGN KEY([id_film])
REFERENCES [dbo].[ThongTinPhim] ([id])
GO
ALTER TABLE [dbo].[TheLoaiPhim]  WITH CHECK ADD FOREIGN KEY([id_the_loai])
REFERENCES [dbo].[TheLoai] ([id])
GO
ALTER TABLE [dbo].[ThongTinPhim]  WITH CHECK ADD FOREIGN KEY([id_ngon_ngu])
REFERENCES [dbo].[NgonNgu] ([id])
GO
ALTER TABLE [dbo].[ThongTinPhim]  WITH CHECK ADD FOREIGN KEY([id_quoc_gia])
REFERENCES [dbo].[QuocGia] ([id])
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD FOREIGN KEY([id_ghe])
REFERENCES [dbo].[Ghe] ([id])
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD FOREIGN KEY([id_hoa_don])
REFERENCES [dbo].[HoaDon] ([id])
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD FOREIGN KEY([id_lich_chieu])
REFERENCES [dbo].[LichChieu] ([id])
GO
USE [master]
GO
ALTER DATABASE [Cinema_Ticket] SET  READ_WRITE 
GO
=======
﻿USE [master]
GO
/****** Object:  Database [Cinema_Ticket]    Script Date: 6/29/2023 2:46:05 AM ******/
CREATE DATABASE [Cinema_Ticket]
GO
ALTER DATABASE [Cinema_Ticket] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET ARITHABORT OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Cinema_Ticket] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Cinema_Ticket] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Cinema_Ticket] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Cinema_Ticket] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET RECOVERY FULL 
GO
ALTER DATABASE [Cinema_Ticket] SET  MULTI_USER 
GO
ALTER DATABASE [Cinema_Ticket] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Cinema_Ticket] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Cinema_Ticket] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Cinema_Ticket] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Cinema_Ticket] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Cinema_Ticket] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Cinema_Ticket', N'ON'
GO
ALTER DATABASE [Cinema_Ticket] SET QUERY_STORE = ON
GO
ALTER DATABASE [Cinema_Ticket] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [Cinema_Ticket]
GO
/****** Object:  Table [dbo].[ChiTietThanhToan]    Script Date: 6/29/2023 2:46:05 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietThanhToan](
	[id_hoa_don] [uniqueidentifier] NULL,
	[id_phuong_thuc_thanh_toan] [uniqueidentifier] NULL,
	[tong_tien] [money] DEFAULT 0,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChucVu](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](100) NULL,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Combo]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Combo](
	[id] [uniqueidentifier] NOT NULL,
	[ten_combo] [nvarchar](255) NULL,
	[gia] [money] DEFAULT 0,
	[deleted] [bit] DEFAULT 0 ,
	[update_by] [nvarchar](255) NULL,
	[update_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[create_at] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ComboDoAnDetail]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ComboDoAnDetail](
	[id_combo] [uniqueidentifier] NOT NULL,
	[id_do_an] [uniqueidentifier] NOT NULL,
	[deleted] [bit] DEFAULT 0 ,
	[so_luong] [int] DEFAULT 0,
	[create_at] [datetime] NULL,
	[update_by] [uniqueidentifier] NULL,
	[update_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_combo] ASC,
	[id_do_an] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DoAn]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DoAn](
	[id] [uniqueidentifier] NOT NULL,
	[Ten] [nvarchar](255) NULL,
	[Gia] [money] DEFAULT 0,
	[create_at] [datetime] NULL,
	[deleted] [bit] DEFAULT 0 ,
	[update_by] [uniqueidentifier] NULL,
	[update_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ghe]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ghe](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](10) NULL,
	[id_loai_ghe] [uniqueidentifier] NULL,
	[id_phong_chieu] [uniqueidentifier] NULL,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GiaVeLichChieu]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GiaVeLichChieu](
	[id_lich_chieu] [uniqueidentifier] NOT NULL,
	[id_loai_ghe] [uniqueidentifier] NOT NULL,
	[gia] [money] DEFAULT 0,
	[so_luong_ghe] [int] DEFAULT 0,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id_lich_chieu] ASC,
	[id_loai_ghe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[id] [uniqueidentifier] NOT NULL,
	[id_nhan_vien] [uniqueidentifier] NULL,
	[id_khach_hang] [uniqueidentifier] NULL,
	[ghi_chu] [nvarchar](max) NULL,
	[tong_gia] [money] DEFAULT 0,
	[tong_gia_sau_giam] [money] DEFAULT 0,
	[trang_thai] [int] DEFAULT 0,
	[thoi_gian_thanh_toan] [datetime] NULL,
	[create_at] [datetime] NULL,
	 [update_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonDoAn]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonDoAn](
	[id_hoa_don] [uniqueidentifier] NOT NULL,
	[id_combo] [uniqueidentifier] NOT NULL,
	[soluong] [int] DEFAULT 0,
	[gia] [money] DEFAULT 0,
	[deleted] [bit] DEFAULT 0 ,
	[update_by] [uniqueidentifier] NULL,
	[update_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[create_at] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_hoa_don] ASC,
	[id_combo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[id] [uniqueidentifier] NOT NULL,
	[ho_ten] [nvarchar](100) NULL,
	[email] [nvarchar](200) NULL,
	  [mat_khau] [varchar](20) NULL,
	[gioi_tinh] [bit] NULL,
	[ngay_sinh] [date] NULL,
	[so_dien_thoai] [nvarchar](50) NULL,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LichChieu]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LichChieu](
	[id] [uniqueidentifier] NOT NULL,
	[id_phim] [uniqueidentifier] NULL,
	[gio_chieu] [time](7) NULL,
	[gio_ket_thuc] [time](7) NULL,
	[ngay_chieu] [datetime] NULL,
	[id_phong_chieu] [uniqueidentifier] NULL,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiGhe]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiGhe](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](20) NULL,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NgonNgu]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NgonNgu](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](20) NULL,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[id] [uniqueidentifier] NOT NULL,
	[id_chuc_vu] [uniqueidentifier] NULL,
	[ho_ten] [nvarchar](100) NULL,
	 [mat_khau] [varchar](20) NULL,
	[email] [nvarchar](100) NULL,
	[so_dien_thoai] [nvarchar](10) NULL,
	[image_nv] [nvarchar](max) NULL,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhongChieu]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhongChieu](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](20) NULL,
	[so_luong_ghe] [int] DEFAULT 0,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhuongThucThanhToan]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhuongThucThanhToan](
	[id] [uniqueidentifier] NOT NULL,
	[hinh_thuc_thanh_toan] [nvarchar](100) NULL,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QuocGia]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuocGia](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](20) NULL,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TheLoai]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TheLoai](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](100) NULL,
	[mo_ta] [nvarchar](255) NULL,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TheLoaiPhim]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TheLoaiPhim](
	[id_film] [uniqueidentifier] NOT NULL,
	[id_the_loai] [uniqueidentifier] NOT NULL,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id_film] ASC,
	[id_the_loai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ThongTinPhim]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThongTinPhim](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](20) NULL,
	[dao_dien] [nvarchar](20) NULL,
	[nha_san_xuat] [nvarchar](20) NULL,
	[dien_vien] [nvarchar](20) NULL,
	[nam_phat_hanh] [int] DEFAULT 0,
	[thoi_luong] [int] DEFAULT 0,
	[tuoi_gioi_han] [int] DEFAULT 0,
	[id_quoc_gia] [uniqueidentifier] NULL,
	[id_ngon_ngu] [uniqueidentifier] NULL,
	-- [ngay_khoi_chieu] [date] NULL,
	[noidung] [nvarchar](max) NULL,
	[trailer] [nvarchar](max) NULL,
	[poster] [nvarchar](max) NULL,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ve]    Script Date: 6/29/2023 2:46:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ve](
	[id_hoa_don] [uniqueidentifier] NULL,
	[id_lich_chieu] [uniqueidentifier] NOT NULL,
	[id_ghe] [uniqueidentifier] NOT NULL,
	[gia] [money] DEFAULT 0,
	[ngay_dat_ve] [date] NULL,
	[trang_thai] [int] DEFAULT 0,
	[update_at] [datetime] NULL,
	[create_at] [datetime] NULL,
	[create_by] [uniqueidentifier] NULL,
	[update_by] [uniqueidentifier] NULL,
	[deleted] [bit] DEFAULT 0 ,
PRIMARY KEY CLUSTERED 
(
	[id_lich_chieu] ASC,
	[id_ghe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChucVu] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[Combo] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[DoAn] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[Ghe] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[KhachHang] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[LichChieu] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[LoaiGhe] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[NgonNgu] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[PhongChieu] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[PhuongThucThanhToan] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[QuocGia] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[TheLoai] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[ThongTinPhim] ADD  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[ChiTietThanhToan]  WITH CHECK ADD FOREIGN KEY([id_hoa_don])
REFERENCES [dbo].[HoaDon] ([id])
GO
ALTER TABLE [dbo].[ChiTietThanhToan]  WITH CHECK ADD FOREIGN KEY([id_phuong_thuc_thanh_toan])
REFERENCES [dbo].[PhuongThucThanhToan] ([id])
GO
ALTER TABLE [dbo].[ComboDoAnDetail]  WITH CHECK ADD FOREIGN KEY([id_combo])
REFERENCES [dbo].[Combo] ([id])
GO
ALTER TABLE [dbo].[ComboDoAnDetail]  WITH CHECK ADD FOREIGN KEY([id_do_an])
REFERENCES [dbo].[DoAn] ([id])
GO
ALTER TABLE [dbo].[Ghe]  WITH CHECK ADD FOREIGN KEY([id_loai_ghe])
REFERENCES [dbo].[LoaiGhe] ([id])
GO
ALTER TABLE [dbo].[Ghe]  WITH CHECK ADD FOREIGN KEY([id_phong_chieu])
REFERENCES [dbo].[PhongChieu] ([id])
GO
ALTER TABLE [dbo].[GiaVeLichChieu]  WITH CHECK ADD FOREIGN KEY([id_lich_chieu])
REFERENCES [dbo].[LichChieu] ([id])
GO
ALTER TABLE [dbo].[GiaVeLichChieu]  WITH CHECK ADD FOREIGN KEY([id_loai_ghe])
REFERENCES [dbo].[LoaiGhe] ([id])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([id_khach_hang])
REFERENCES [dbo].[KhachHang] ([id])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([id_nhan_vien])
REFERENCES [dbo].[NhanVien] ([id])
GO
ALTER TABLE [dbo].[HoaDonDoAn]  WITH CHECK ADD FOREIGN KEY([id_combo])
REFERENCES [dbo].[Combo] ([id])
GO
ALTER TABLE [dbo].[HoaDonDoAn]  WITH CHECK ADD FOREIGN KEY([id_hoa_don])
REFERENCES [dbo].[HoaDon] ([id])
GO
ALTER TABLE [dbo].[LichChieu]  WITH CHECK ADD FOREIGN KEY([id_phim])
REFERENCES [dbo].[ThongTinPhim] ([id])
GO
ALTER TABLE [dbo].[LichChieu]  WITH CHECK ADD FOREIGN KEY([id_phong_chieu])
REFERENCES [dbo].[PhongChieu] ([id])
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD FOREIGN KEY([id_chuc_vu])
REFERENCES [dbo].[ChucVu] ([id])
GO
ALTER TABLE [dbo].[TheLoaiPhim]  WITH CHECK ADD FOREIGN KEY([id_film])
REFERENCES [dbo].[ThongTinPhim] ([id])
GO
ALTER TABLE [dbo].[TheLoaiPhim]  WITH CHECK ADD FOREIGN KEY([id_the_loai])
REFERENCES [dbo].[TheLoai] ([id])
GO
ALTER TABLE [dbo].[ThongTinPhim]  WITH CHECK ADD FOREIGN KEY([id_ngon_ngu])
REFERENCES [dbo].[NgonNgu] ([id])
GO
ALTER TABLE [dbo].[ThongTinPhim]  WITH CHECK ADD FOREIGN KEY([id_quoc_gia])
REFERENCES [dbo].[QuocGia] ([id])
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD FOREIGN KEY([id_ghe])
REFERENCES [dbo].[Ghe] ([id])
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD FOREIGN KEY([id_hoa_don])
REFERENCES [dbo].[HoaDon] ([id])
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD FOREIGN KEY([id_lich_chieu])
REFERENCES [dbo].[LichChieu] ([id])
GO
USE [master]
GO
ALTER DATABASE [Cinema_Ticket] SET  READ_WRITE 
GO
>>>>>>> 95e71c7cad4ef7c1b8758181ea4d6e97ef913db6
