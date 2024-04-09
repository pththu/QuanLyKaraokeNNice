package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entities.LoaiPhong;
import entities.PhongEntity;
import util.ConnectDB;

public class QuanLyPhongDAO {
	PhongEntity phongEntity;
	LoaiPhong loaiPhong;

	public QuanLyPhongDAO() {

	}

	/***** LOẠI PHÒNG *****/

	/**
	 * Tìm theo mã loại phòng
	 * 
	 */
	public LoaiPhong timTheoMaLoaiPhong(String maLoaiPhong) {
		LoaiPhong loaiPhongKetQua = null;
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "SELECT *FROM LoaiPhong WHERE MaLoaiPhong LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maLoaiPhong);
				result = statement.executeQuery();
				while (result.next()) {
					String tenLoai = result.getString(2);
					loaiPhongKetQua = new LoaiPhong(maLoaiPhong, tenLoai);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closeResultSet(result);
				ConnectDB.closePreStatement(statement);
			}
		}
		return loaiPhongKetQua;
	}

	/**
	 * Tìm theo tên loại phòng
	 * 
	 */
	public LoaiPhong timTheoTenLoaiPhong(String tenLoaiPhong) {
		LoaiPhong loaiPhongKetQua = null;
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "SELECT *FROM LoaiPhong WHERE TenLoai LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, tenLoaiPhong);
				result = statement.executeQuery();
				while (result.next()) {
					String maLoaiPhong = result.getString(1);
					String tenLoai = result.getString(2);
					loaiPhongKetQua = new LoaiPhong(maLoaiPhong, tenLoai);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closeResultSet(result);
				ConnectDB.closePreStatement(statement);
			}
		}
		return loaiPhongKetQua;
	}

	/**
	 * duyệt toàn bộ danh sách loại phòng
	 */
	public List<LoaiPhong> duyetDanhSachLoaiPhong() {
		List<LoaiPhong> listLoaiPhong = new ArrayList<LoaiPhong>();
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		Statement statement = null;
		try {
			String query = "SELECT *FROM LoaiPhong";
			statement = connect.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				String maLoaiPhong = result.getString(1);
				String tenLoai = result.getString(2);

				loaiPhong = new LoaiPhong(maLoaiPhong, tenLoai);
				listLoaiPhong.add(loaiPhong);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnect(connect);
			ConnectDB.closeStatement(statement);
			ConnectDB.closeResultSet(result);
		}
		return listLoaiPhong;
	}

	/**
	 * thêm loại phòng
	 */
	public boolean themLoaiPhong(LoaiPhong loaiPhong) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "INSERT LoaiPhong (TenLoai)\r\n" + "VALUES (?)";
				statement = connect.prepareStatement(query);
				statement.setString(1, loaiPhong.getTenLoaiPhong());
				return statement.executeUpdate() > 0;
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statement);
			}
		}
		return false;
	}

	/**
	 * Xóa loại phòng
	 */
	public boolean xoaLoaiPhong(String maLoaiPhong) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;

		if (connect != null) {
			try {
				String query = "DELETE LoaiPhong WHERE MaLoaiPhong LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maLoaiPhong);
				return statement.executeUpdate() > 0;
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statement);
			}
		}

		return false;
	}

	/**
	 * Chỉnh sửa loại phòng
	 */
	public boolean chinhSuaLoaiPhong(LoaiPhong loaiPhong) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;

		if (connect != null) {
			try {
				String query = "UPDATE LoaiPhong\r\n" + "SET TenLoai = ?\r\n" + "WHERE MaLoaiPhong LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, loaiPhong.getTenLoaiPhong());
				statement.setString(2, loaiPhong.getMaLoai());
				return statement.executeUpdate() > 0;
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statement);
			}
		}
		return false;
	}

	/***** PHÒNG *****/

	/**
	 * duyệt toàn bộ danh sách phòng
	 */
	public List<PhongEntity> duyetDanhSach() {
		List<PhongEntity> listPhong = new ArrayList<PhongEntity>();
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		Statement statement = null;
		try {
			String query = "SELECT MaPhong, SoPhong, LP.MaLoaiPhong, TenLoai, SucChua, TrangThai\r\n"
					+ "FROM [dbo].[Phong] P JOIN [dbo].[LoaiPhong] LP ON P.MaLoaiPhong = LP.MaLoaiPhong";
			statement = connect.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				String maPhong = result.getString(1);
				int soPhong = result.getInt(2);
				String maLoaiPhong = result.getString(3);
				String tenLoai = result.getString(4);
				int sucChua = result.getInt(5);
				String trangThai = result.getString(6);

				phongEntity = new PhongEntity(maPhong, soPhong, new LoaiPhong(maLoaiPhong, tenLoai), trangThai,
						sucChua);
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

		return listPhong;
	}

	/**
	 * Thêm phòng
	 */
	public boolean themPhong(PhongEntity phongEntity) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "INSERT Phong (SoPhong, MaLoaiPhong, SucChua, TrangThai)\r\n" + "VALUES(?, ?, ?, ?)";
				statement = connect.prepareStatement(query);
				statement.setInt(1, phongEntity.getSoPhong());
				statement.setString(2, phongEntity.getLoaiPhong().getMaLoai());
				statement.setInt(3, phongEntity.getSucChua());
				statement.setString(4, phongEntity.getTrangThai());
				return statement.executeUpdate() > 0;
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statement);
			}
		}
		return false;
	}

	/**
	 * Chỉnh sửa phòng
	 */
	public boolean chinhSuaPhong(PhongEntity phongEntity) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;

		if (connect != null) {
			try {
				String query = "UPDATE Phong\r\n" + "SET SoPhong = ?, MaLoaiPhong = ?, SucChua = ?\r\n"
						+ "WHERE MaPhong LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setInt(1, phongEntity.getSoPhong());
				statement.setString(2, phongEntity.getLoaiPhong().getMaLoai());
				statement.setInt(3, phongEntity.getSucChua());
				statement.setString(4, phongEntity.getMaPhong());
				return statement.executeUpdate() > 0;
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statement);
			}
		}
		return false;
	}

	/**
	 * Xóa phòng
	 */
	public boolean xoaPhong(String maPhong) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;

		if (connect != null) {
			try {
				String query = "DELETE Phong WHERE MaPhong LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maPhong);
				return statement.executeUpdate() > 0;
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statement);
			}
		}
		return false;
	}

	/**
	 * Tìm kiếm phòng
	 */
	public List<PhongEntity> timKiemPhong(int soPhong, int sucChua, String trangThai, String loaiPhong) {
		List<PhongEntity> listPhong = new ArrayList<PhongEntity>();
		Connection connect = ConnectDB.getConnect();
		Statement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(
						"SELECT MaPhong, SoPhong, LP.MaLoaiPhong, TenLoai, SucChua, TrangThai\r\n"
								+ " FROM [dbo].[Phong] P JOIN [dbo].[LoaiPhong] LP ON P.MaLoaiPhong = LP.MaLoaiPhong ");
				if (soPhong >= 0 && sucChua >= 0 && !trangThai.equals("") && !loaiPhong.equals("Tất cả")) {
					// soPhong + sucChua + trangThai + loaiPhong
					query.append(String.format(
							"WHERE SoPhong = %d AND SucChua = %d AND TrangThai LIKE N'%%%s%%' AND TenLoai LIKE N'%%%s%%'",
							soPhong, sucChua, trangThai, loaiPhong));
				} else if (soPhong >= 0 && sucChua == -1 && trangThai.equals("") && loaiPhong.equals("Tất cả")) {
					// soPhong
					query.append(String.format("WHERE SoPhong = %d", soPhong));
				} else if (soPhong >= 0 && sucChua >= 0 && trangThai.equals("") && loaiPhong.equals("Tất cả")) {
					// soPhong + sucChua
					query.append(String.format("WHERE SoPhong = %d AND SucChua = %d", soPhong, sucChua));
				} else if (soPhong >= 0 && sucChua >= 0 && !trangThai.equals("") && loaiPhong.equals("Tất cả")) {
					// soPhong + sucChua + trangThai
					query.append(String.format("WHERE SoPhong = %d AND SucChua = %d AND TrangThai LIKE N'%%%s%%'",
							soPhong, sucChua, trangThai));
				} else if (soPhong >= 0 && sucChua >= 0 && trangThai.equals("") && !loaiPhong.equals("Tất cả")) {
					// soPhong + sucChua + loaiPhong
					query.append(String.format("WHERE SoPhong = %d AND SucChua = %d AND TenLoai LIKE N'%%%s%%'",
							soPhong, sucChua, loaiPhong));
				} else if (soPhong == -1 && sucChua >= 0 && trangThai.equals("") && loaiPhong.equals("Tất cả")) {
					// sucChua
					query.append(String.format("WHERE SucChua = %d", sucChua));
				} else if (soPhong == -1 && sucChua >= 0 && !trangThai.equals("") && loaiPhong.equals("Tất cả")) {
					// sucChua + trangThai
					query.append(String.format("WHERE SucChua = %d AND TrangThai LIKE N'%%%s%%'", sucChua, trangThai));
				} else if (soPhong == -1 && sucChua >= 0 && trangThai.equals("") && !loaiPhong.equals("Tất cả")) {
					// sucChua + loaiPhong
					query.append(String.format("WHERE SucChua = %d AND TenLoai LIKE N'%%%s%%'", soPhong, sucChua,
							trangThai, loaiPhong));
				} else if (soPhong == -1 && sucChua >= 0 && !trangThai.equals("") && !loaiPhong.equals("Tất cả")) {
					// sucChua + trangThai + loaiPhong
					query.append(
							String.format("WHERE SucChua = %d AND TrangThai LIKE N'%%%s%%' AND TenLoai LIKE N'%%%s%%'",
									sucChua, trangThai, loaiPhong));

				} else if (soPhong == -1 && sucChua == -1 && !trangThai.equals("") && loaiPhong.equals("Tất cả")) {
					// trangThai
					query.append(String.format("WHERE TrangThai LIKE N'%%%s%%'", trangThai));
				} else if (soPhong == -1 && sucChua == -1 && !trangThai.equals("") && !loaiPhong.equals("Tất cả")) {
					// trangThai + loaiPhong
					query.append(String.format("WHERE TrangThai LIKE N'%%%s%%' AND TenLoai LIKE N'%%%s%%'", trangThai,
							loaiPhong));
				} else if (soPhong == -1 && sucChua == -1 && trangThai.equals("") && !loaiPhong.equals("Tất cả")) {
					// loaiPhong
					query.append(String.format("WHERE TenLoai LIKE N'%%%s%%'", loaiPhong));
				}

				statement = connect.createStatement();
				result = statement.executeQuery(query.toString());
				while (result.next()) {
					String maPhong = result.getString(1);
					int soPh = result.getInt(2);
					String maLoaiPhong = result.getString(3);
					String tenLoai = result.getString(4);
					int sucChuaPh = result.getInt(5);
					String trangThaiPh = result.getString(6);

					phongEntity = new PhongEntity(maPhong, soPh, new LoaiPhong(maLoaiPhong, tenLoai), trangThaiPh,
							sucChuaPh);
					listPhong.add(phongEntity);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closeResultSet(result);
				ConnectDB.closeStatement(statement);
			}
		}
		return listPhong;
	}

	public boolean capNhatTrangThaiPhong(PhongEntity phongEntity, String trangThai) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;

		if (connect != null) {
			try {
				String query = "UPDATE Phong\r\n" + "SET TrangThai = ?\r\n" + "WHERE MaPhong LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, trangThai);
				statement.setString(2, phongEntity.getMaPhong());
				return statement.executeUpdate() > 0;
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statement);
			}
		}
		return false;
	}

	public PhongEntity timTheoMa(String maPhong) {
		PhongEntity phongEntity = null;
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				String query = "SELECT MaPhong, SoPhong, LP.MaLoaiPhong, TenLoai, TrangThai, SucChua\r\n"
						+ "FROM Phong P JOIN LoaiPhong LP ON  P.MaLoaiPhong = LP.MaLoaiPhong WHERE MaPhong LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maPhong);
				result = statement.executeQuery();
				while (result.next()) {
					int soPhong = result.getInt(2);
					String maLoaiPhong = result.getString(3);
					String tenLoai = result.getString(4);
					String trangThai = result.getString(5);
					int sucChua = result.getInt(6);
					phongEntity = new PhongEntity(maPhong, soPhong, new LoaiPhong(maLoaiPhong, tenLoai), trangThai,
							sucChua);
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
		return phongEntity;
	}

}
