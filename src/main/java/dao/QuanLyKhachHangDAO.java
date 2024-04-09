package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.KhachHangEntity;
import util.ConnectDB;

public class QuanLyKhachHangDAO {

	KhachHangEntity khachHangEntity;

	public QuanLyKhachHangDAO() {

	}

	// Duyệt Danh sách

	public List<KhachHangEntity> duyetDanhSach() {
		List<KhachHangEntity> list = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		Statement statement = null;

		if (connect != null) {
			try {
				String query = "SELECT * FROM KhachHang";
				statement = connect.createStatement();
				result = statement.executeQuery(query);
				while (result.next()) {
					String MaKhachHang = result.getString(1);
					String hoTen = result.getString(2);
					String sdt = result.getString(3);
					String email = result.getString(4);
					int namSinh = result.getInt(5);
					int soLanDatPhong = result.getInt(6);
					khachHangEntity = new KhachHangEntity(MaKhachHang, hoTen, sdt, email, namSinh, soLanDatPhong);
					list.add(khachHangEntity);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closeStatement(statement);
				ConnectDB.closeResultSet(result);
			}
		}
		return list;
	}

	public KhachHangEntity them(KhachHangEntity khachHangEntity) {
		KhachHangEntity khachHangEntity2 = null;
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "INSERT INTO KhachHang" + "([HoTen],[SoDienThoai],[Email],[NamSinh],[SoLanDatPhong])"
						+ "VALUES (?, ?, ?, ?, ?)";
				statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, khachHangEntity.getHoTen());
				statement.setString(2, khachHangEntity.getSoDienThoai());
				statement.setString(3, khachHangEntity.getEmail());
				statement.setInt(4, khachHangEntity.getNamSinh());
				statement.setInt(5, khachHangEntity.getSoLanDatPhong());
				statement.executeUpdate();
				result = statement.getGeneratedKeys();
				while (result.next()) {
					khachHangEntity2 = new KhachHangEntity();
					khachHangEntity2.setMaKhachHang(result.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statement);
				ConnectDB.closeResultSet(result);
			}
		}

		return khachHangEntity2;

	}

	public int chinhSua(KhachHangEntity khachHangEntity) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;

		if (connect != null) {
			try {
				String query = "UPDATE KhachHang \r\n"
						+ "SET HoTen = ?, SoDienThoai = ?, Email = ? , NamSinh = ? , SoLanDatPhong = ?\r\n"
						+ "where MaKhachHang LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, khachHangEntity.getHoTen());
				statement.setString(2, khachHangEntity.getSoDienThoai());
				statement.setString(3, khachHangEntity.getEmail());
				statement.setInt(4, khachHangEntity.getNamSinh());
				statement.setInt(5, khachHangEntity.getSoLanDatPhong());
				statement.setString(6, khachHangEntity.getMaKhachHang());
				return statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statement);
			}
		}

		return 0;

	}

	public KhachHangEntity timTheoMa(String MaKhachHang) {
		KhachHangEntity khachhangKq = new KhachHangEntity();
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "SELECT MaKhachHang, HoTen, SoDienThoai, Email, NamSinh, SoLanDatPhong\r\n"
						+ "FROM KhachHang WHERE MaKhachHang LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, MaKhachHang);
				result = statement.executeQuery();
				while (result.next()) {
					String hoTen = result.getString(2);
					String sdt = result.getString(3);
					String email = result.getString(4);
					int namSinh = result.getInt(5);
					int soLanDatPhong = result.getInt(6);
					khachhangKq = new KhachHangEntity(MaKhachHang, hoTen, sdt, email, namSinh, soLanDatPhong);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closeResultSet(result);
				ConnectDB.closePreStatement(statement);
			}
		}
		return khachhangKq;
	}

	public KhachHangEntity timTheoSoDienThoai(String soDienThoai) {
		KhachHangEntity khachhangKetQua = null;
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "SELECT MaKhachHang, HoTen, SoDienThoai, Email, NamSinh, SoLanDatPhong\r\n"
						+ "FROM KhachHang WHERE SoDienThoai LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, soDienThoai);
				result = statement.executeQuery();
				while (result.next()) {
					String MaKhachHangachHang = result.getString(1);
					String hoTen = result.getString(2);
					String sdt = result.getString(3);
					String email = result.getString(4);
					int namSinh = result.getInt(5);
					int soLanDatPhong = result.getInt(6);
					khachhangKetQua = new KhachHangEntity(MaKhachHangachHang, hoTen, sdt, email, namSinh,
							soLanDatPhong);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closeResultSet(result);
				ConnectDB.closePreStatement(statement);
			}
		}
		return khachhangKetQua;
	}

	public List<KhachHangEntity> timKiem(String hoTen, String soDienThoai, int slTu, int slDen) {
		List<KhachHangEntity> listKhachHang = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		Statement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder("SELECT * FROM KhachHang ");
				if (!hoTen.isEmpty() && !soDienThoai.isEmpty() && (slTu >= 0 && slDen >= 0)) {
					// hoTen, soDienThoai, slTu, slDen
					query.append(String.format(
							"WHERE HoTen LIKE N'%%%s%%' AND SoDienThoai LIKE '%s' AND (SoLanDatPhong >= %s AND SoLanDatPhong <= %s)",
							hoTen, soDienThoai, slTu, slDen));
				} else if (!hoTen.isEmpty() && soDienThoai.isEmpty() && (slTu == -1 && slDen == -1)) {
					// hoTen
					query.append(String.format("WHERE HoTen LIKE N'%%%s%%'", hoTen));
				} else if (hoTen.isEmpty() && !soDienThoai.isEmpty() && (slTu == -1 && slDen == -1)) {
					// soDienThoai
					query.append(String.format("WHERE SoDienThoai LIKE '%%%s%%'", soDienThoai));
				} else if (hoTen.isEmpty() && soDienThoai.isEmpty() && (slTu >= 0 && slDen >= 0)) {
					// slTu, slDen
					query.append(String.format("WHERE (SoLanDatPhong >= %s AND SoLanDatPhong <= %s)", slTu, slDen));
				} else if (!hoTen.isEmpty() && !soDienThoai.isEmpty() && (slTu == -1 && slDen == -1)) {
					// hoTen, soDienThoai
					query.append(String.format("WHERE HoTen LIKE N'%%%s%%' AND SoDienThoai LIKE '%%%s%%'", hoTen,
							soDienThoai));
				} else if (!hoTen.isEmpty() && soDienThoai.isEmpty() && (slTu >= 0 && slDen >= 0)) {
					// hoTen, slTu, slDen
					query.append(String.format(
							"WHERE HoTen LIKE N'%%%s%%' AND (SoLanDatPhong >= %s AND SoLanDatPhong <= %s)", hoTen, slTu,
							slDen));
				} else if (hoTen.isEmpty() && !soDienThoai.isEmpty() && (slTu >= 0 && slDen >= 0)) {
					// soDienThoai, slTu, slDen
					query.append(String.format(
							"WHERE SoDienThoai LIKE '%%%s%%' AND (SoLanDatPhong >= %s AND SoLanDatPhong <= %s)",
							soDienThoai, slTu, slDen));
				}
				statement = connect.createStatement();
				result = statement.executeQuery(query.toString());
				while (result.next()) {
					String MaKhachHang = result.getString(1);
					String tenKH = result.getString(2);
					String sdt = result.getString(3);
					String email = result.getString(4);
					int namSinh = result.getInt(5);
					int soLanDatPhong = result.getInt(6);
					KhachHangEntity khachHangEntity = new KhachHangEntity(MaKhachHang, tenKH, sdt, email, namSinh,
							soLanDatPhong);
					listKhachHang.add(khachHangEntity);
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closeResultSet(result);
				ConnectDB.closeStatement(statement);
			}
		}

		return listKhachHang;

	}

}
