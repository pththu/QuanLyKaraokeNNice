package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entities.HoaDonEntity;
import entities.NhanVienEntity;
import util.ConnectDB;
import util.DateFormatter;

public class ThongKeDAO {

	public ThongKeDAO() {

	}

	/************************************
	 * HÓA ĐƠN
	 *********************************************/
	public List<HoaDonEntity> duyetDanhSachHoaDonTheoNgay(LocalDate ngay) {
		List<HoaDonEntity> listHoaDon = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statemant = null;
		ResultSet result = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM HoaDon\r\n" + "WHERE NgayLap LIKE ?";
				statemant = connect.prepareStatement(query);
				statemant.setString(1, DateFormatter.formatSql(ngay));
				result = statemant.executeQuery();
				while (result.next()) {
					String maHoaDon = result.getString(1);
					String maNhanVien = result.getString(2);
					String maKhachHang = result.getString(3);
					LocalDate ngayLap = result.getDate(4).toLocalDate();
					LocalTime gioLap = result.getTime(5).toLocalTime();
					HoaDonEntity hoaDonEntity = new HoaDonEntity(maHoaDon, maNhanVien, maKhachHang, ngayLap, gioLap);
					listHoaDon.add(hoaDonEntity);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statemant);
				ConnectDB.closeResultSet(result);
			}
		}
		return listHoaDon;
	}

	public List<HoaDonEntity> duyetDanhSachHoaDonTheoNamThang(LocalDate ngay) {
		List<HoaDonEntity> listHoaDon = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statemant = null;
		ResultSet result = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM HoaDon\r\n"
						+ "WHERE DATEPART(MM, NgayLap) = ? AND DATEPART(yyyy, NgayLap) = ?";
				statemant = connect.prepareStatement(query);
				statemant.setInt(1, ngay.getMonthValue());
				statemant.setInt(2, ngay.getYear());
				result = statemant.executeQuery();
				while (result.next()) {
					String maHoaDon = result.getString(1);
					String maNhanVien = result.getString(2);
					String maKhachHang = result.getString(3);
					LocalDate ngayLap = result.getDate(4).toLocalDate();
					LocalTime gioLap = result.getTime(5).toLocalTime();
					HoaDonEntity hoaDonEntity = new HoaDonEntity(maHoaDon, maNhanVien, maKhachHang, ngayLap, gioLap);
					listHoaDon.add(hoaDonEntity);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statemant);
				ConnectDB.closeResultSet(result);
			}
		}
		return listHoaDon;
	}

	public List<HoaDonEntity> duyetDanhSachHoaDonTheoNam(LocalDate ngay) {
		List<HoaDonEntity> listHoaDon = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statemant = null;
		ResultSet result = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM HoaDon\r\n" + "WHERE DATEPART(yyyy, NgayLap) = ?";
				statemant = connect.prepareStatement(query);
				statemant.setInt(1, ngay.getYear());
				result = statemant.executeQuery();
				while (result.next()) {
					String maHoaDon = result.getString(1);
					String maNhanVien = result.getString(2);
					String maKhachHang = result.getString(3);
					LocalDate ngayLap = result.getDate(4).toLocalDate();
					LocalTime gioLap = result.getTime(5).toLocalTime();
					HoaDonEntity hoaDonEntity = new HoaDonEntity(maHoaDon, maNhanVien, maKhachHang, ngayLap, gioLap);
					listHoaDon.add(hoaDonEntity);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statemant);
				ConnectDB.closeResultSet(result);
			}
		}
		return listHoaDon;
	}

	public double tinhTongTienDichVuCuaHoaDon(HoaDonEntity hoaDonEntity) {
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		PreparedStatement statemant = null;
		if (connect != null) {
			try {
				String query = "SELECT SUM((SoLuong*Gia)) AS N'Tổng' FROM ChiTietDichVu CTDV\r\n"
						+ "JOIN ChiTietHoaDon CTHD ON CTHD.MaChiTietHoaDon = CTDV.MaChiTietHoaDon\r\n"
						+ "JOIN HoaDon HD ON HD.MaHoaDon = CTHD.MaHoaDon \r\n"
						+ "JOIN DichVu DV ON DV.MaDichVu = CTDV.MaDichVu\r\n" + "WHERE CTHD.MaHoaDon = ?";
				statemant = connect.prepareStatement(query);
				statemant.setString(1, hoaDonEntity.getMaHoaDon());
				result = statemant.executeQuery();
				while (result.next()) {
					return result.getDouble(1);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statemant);
				ConnectDB.closeResultSet(result);
			}
		}
		return 0;
	}
}
