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

import entities.ChiTietDatPhongEntity;
import entities.LoaiPhong;
import entities.PhongEntity;
import util.ConnectDB;
import util.DateFormatter;
import util.TimeFormatter;

public class DatPhongDAO {
	public DatPhongDAO() {

	}

	private QuanLyPhongDAO quanLyPhongDAO = new QuanLyPhongDAO();

	/************************************
	 * TÌM KIẾM PHÒNG
	 ************************************/

	public List<PhongEntity> timPhong(String trangThai, String loaiPhong, int soPhong, int sucChua) {
		List<PhongEntity> listPhong = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		Statement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(
						"SELECT MaPhong, SoPhong, LP.MaLoaiPhong, TenLoai, SucChua, TrangThai\r\n"
								+ "FROM Phong P JOIN LoaiPhong LP ON P.MaLoaiPhong = LP.MaLoaiPhong ");
				if (!trangThai.equals("Tất cả") && !loaiPhong.equals("Tất cả") && soPhong >= 0 && sucChua >= 0) {
					// 1 trangThai + loaiPhong + soPhong + sucChua
					query.append(String.format(
							"WHERE TrangThai LIKE N'%%%s%%' AND TenLoai LIKE N'%%%s%%' AND SoPhong LIKE '%%%d%%' AND SucChua = %d",
							trangThai, loaiPhong, soPhong, sucChua));
				} else if (!trangThai.equals("Tất cả") && !loaiPhong.equals("Tất cả") && soPhong >= 0
						&& !(sucChua >= 0)) {
					// 2 trangThai + loaiPhong + soPhong
					query.append(String.format(
							"WHERE TrangThai LIKE N'%%%s%%' AND TenLoai LIKE N'%%%s%%' AND SoPhong LIKE '%%%d%%'",
							trangThai, loaiPhong, soPhong));
				} else if (!trangThai.equals("Tất cả") && !loaiPhong.equals("Tất cả") && !(soPhong >= 0)
						&& sucChua >= 0) {
					// 3 trangThai + loaiPhong + sucChua
					query.append(
							String.format("WHERE TrangThai LIKE N'%%%s%%' AND TenLoai LIKE N'%%%s%%' AND SucChua = %d",
									trangThai, loaiPhong, sucChua));
				} else if (!trangThai.equals("Tất cả") && loaiPhong.equals("Tất cả") && soPhong >= 0 && sucChua >= 0) {
					// 4 trangThai + soPhong + sucChua
					query.append(
							String.format("WHERE TrangThai LIKE N'%%%s%%' AND SoPhong LIKE '%%%d%%' AND SucChua = %d",
									trangThai, soPhong, sucChua));
				} else if (trangThai.equals("Tất cả") && !loaiPhong.equals("Tất cả") && soPhong >= 0 && sucChua >= 0) {
					// 5 loaiPhong + soPhong + sucChua
					query.append(
							String.format("WHERE TenLoai LIKE N'%%%s%%' AND SoPhong LIKE '%%%d%%' AND SucChua = %d",
									loaiPhong, soPhong, sucChua));
				} else if (!trangThai.equals("Tất cả") && !loaiPhong.equals("Tất cả") && !(soPhong >= 0)
						&& !(sucChua >= 0)) {
					// 6 trangThai + loaiPhong
					query.append(String.format("WHERE TrangThai LIKE N'%%%s%%' AND TenLoai LIKE N'%%%s%%'", trangThai,
							loaiPhong));
				} else if (!trangThai.equals("Tất cả") && loaiPhong.equals("Tất cả") && soPhong >= 0
						&& !(sucChua >= 0)) {
					// 7 trangThai + soPhong
					query.append(String.format("WHERE TrangThai LIKE N'%%%s%%' AND SoPhong LIKE '%%%d%%'", trangThai,
							soPhong));
				} else if (!trangThai.equals("Tất cả") && loaiPhong.equals("Tất cả") && !(soPhong >= 0)
						&& sucChua >= 0) {
					// 8 trangThai + sucChua
					query.append(String.format("WHERE TrangThai LIKE N'%%%s%%' AND SucChua = %d", trangThai, sucChua));
				} else if (trangThai.equals("Tất cả") && !loaiPhong.equals("Tất cả") && soPhong >= 0
						&& !(sucChua >= 0)) {
					// 9 loaiPhong + soPhong
					query.append(String.format("WHERE TenLoai LIKE N'%%%s%%' AND SoPhong LIKE '%%%d%%'", loaiPhong,
							soPhong));
				} else if (trangThai.equals("Tất cả") && !loaiPhong.equals("Tất cả") && !(soPhong >= 0)
						&& sucChua >= 0) {
					// 10 loaiPhong + sucChua
					query.append(String.format("WHERE TenLoai LIKE N'%%%s%%' AND SucChua = %d", loaiPhong, sucChua));
				} else if (trangThai.equals("Tất cả") && loaiPhong.equals("Tất cả") && soPhong >= 0 && sucChua >= 0) {
					// 11 soPhong + sucChua
					query.append(String.format("WHERE SoPhong LIKE '%%%d%%' AND SucChua = %d", soPhong, sucChua));
				} else if (trangThai.equals("Tất cả") && loaiPhong.equals("Tất cả") && soPhong >= 0
						&& !(sucChua >= 0)) {
					// 12 soPhong
					query.append(String.format("WHERE SoPhong LIKE '%%%d%%'", soPhong));
				} else if (trangThai.equals("Tất cả") && loaiPhong.equals("Tất cả") && !(soPhong >= 0)
						&& sucChua >= 0) {
					// 13 sucChua
					query.append(String.format("WHERE SucChua = %d", sucChua));
				} else if (!trangThai.equals("Tất cả") && loaiPhong.equals("Tất cả") && !(soPhong >= 0)
						&& !(sucChua >= 0)) {
					// 14 trangThai
					query.append(String.format("WHERE TrangThai LIKE N'%%%s%%'", trangThai));
				} else if (trangThai.equals("Tất cả") && !loaiPhong.equals("Tất cả") && !(soPhong >= 0)
						&& !(sucChua >= 0)) {
					// loaiPhong
					query.append(String.format("WHERE TenLoai LIKE N'%%%s%%'", loaiPhong));
				}

				statement = connect.createStatement();
				result = statement.executeQuery(query.toString());
				while (result.next()) {
					String maPhongKetQua = result.getString(1);
					int soPhongKetQua = result.getInt(2);
					int sucChuaKetQua = result.getInt(5);
					String trangThaiKetQua = result.getString(6);
					LoaiPhong loaiPhongKetQua = new LoaiPhong(result.getString(3), result.getString(4));
					PhongEntity phongEntity = new PhongEntity(maPhongKetQua, soPhongKetQua, loaiPhongKetQua,
							trangThaiKetQua, sucChuaKetQua);
					listPhong.add(phongEntity);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closeStatement(statement);
				ConnectDB.closeResultSet(result);
			}
		}
		return listPhong;
	}

	public boolean doiPhong(ChiTietDatPhongEntity chiTietDatPhongEntity, String maPhong) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;

		if (connect != null) {
			try {
				String query = "UPDATE ChiTietDatPhong SET MaPhong = ?\r\n" + "FROM ChiTietDatPhong\r\n"
						+ "WHERE MaChiTietDatPhong LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maPhong);
				statement.setString(2, chiTietDatPhongEntity.getMaChiTietDatPhong());
				return statement.executeUpdate() > 0;
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closeStatement(statement);
			}
		}
		return false;
	}

	public List<PhongEntity> timPhongTrongTheoNgayVaGio(LocalDate ngay, LocalTime gioNhan, LocalTime gioTra) {
		List<PhongEntity> listPhong = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				String query = "SELECT * FROM Phong P\r\n"
						+ "WHERE NOT EXISTS (SELECT MaPhong FROM ChiTietDatPhong CTDP JOIN ChiTietHoaDon CTHD\r\n"
						+ "	ON CTDP.MaChiTietDatPhong = CTHD.MaChiTietDatPhong \r\n"
						+ "	WHERE CTDP.MaPhong = P.MaPhong AND MaHoaDon IS NULL\r\n"
						+ "	AND NgayDatPhong = ? AND (GioNhanPhong >= ? AND GioNhanPhong <= ?) AND (GioTraPhong >= ? AND GioTraPhong <= ?))\r\n";
				statement = connect.prepareStatement(query);
				statement.setString(1, DateFormatter.formatSql(ngay));
				statement.setString(2, TimeFormatter.formatSql(gioNhan.minusMinutes(30)));
				statement.setString(3, TimeFormatter.formatSql(gioTra.plusMinutes(30)));
				statement.setString(4, TimeFormatter.formatSql(gioNhan.minusMinutes(30)));
				statement.setString(5, TimeFormatter.formatSql(gioTra.plusMinutes(30)));
				result = statement.executeQuery();
				while (result.next()) {
					String maPhongKetQua = result.getString(1);
					int soPhongKetQua = result.getInt(2);
					int sucChuaKetQua = result.getInt(5);
					String trangThaiKetQua = result.getString(4);
					LoaiPhong loaiPhongKetQua = quanLyPhongDAO.timTheoMaLoaiPhong(result.getString(3));
					PhongEntity phongEntity = new PhongEntity(maPhongKetQua, soPhongKetQua, loaiPhongKetQua,
							trangThaiKetQua, sucChuaKetQua);
					listPhong.add(phongEntity);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statement);
				ConnectDB.closeResultSet(result);
			}
		}
		return listPhong;
	}

	public List<ChiTietDatPhongEntity> duyetChiTietDatPhongChuaThanhToanTheoPhong(PhongEntity phongEntity) {
		List<ChiTietDatPhongEntity> listChiTietDatPhong = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		ResultSet result = null;
		if (connect != null) {
			try {
				String query = "SELECT CTDP.MaChiTietDatPhong, NgayDatPhong, GioNhanPhong, GioTraPhong \r\n"
						+ "FROM ChiTietDatPhong CTDP JOIN ChiTietHoaDon CTHD \r\n"
						+ "ON CTDP.MaChiTietDatPhong = CTHD.MaChiTietDatPhong\r\n"
						+ "WHERE MaHoaDon IS NULL AND MaPhong = ?\r\n" + "ORDER BY NgayDatPhong ASC, GioNhanPhong ASC";
				statement = connect.prepareStatement(query);
				statement.setString(1, phongEntity.getMaPhong());
				result = statement.executeQuery();
				while (result.next()) {
					String maChiTietDatPhong = result.getString(1);
					LocalDate ngayNhanPhong = result.getDate(2).toLocalDate();
					LocalTime gioNhanPhong = result.getTime(3).toLocalTime();
					LocalTime gioTraPhong = result.getTime(4).toLocalTime();
					ChiTietDatPhongEntity chiTietPhieuDatPhongEntity = new ChiTietDatPhongEntity(maChiTietDatPhong,
							phongEntity, gioNhanPhong, gioTraPhong, ngayNhanPhong);
					listChiTietDatPhong.add(chiTietPhieuDatPhongEntity);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statement);
				ConnectDB.closeResultSet(result);
			}
		}
		return listChiTietDatPhong;
	}
}
