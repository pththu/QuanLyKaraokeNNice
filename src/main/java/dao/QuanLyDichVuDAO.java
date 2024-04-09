package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entities.DichVuEntity;
import entities.LoaiDichVu;
import util.ConnectDB;

public class QuanLyDichVuDAO {
	DichVuEntity dichVuEntity;
	LoaiDichVu loaiDichVu;

	public QuanLyDichVuDAO() {

	}

	// Tìm theo mã loại dịch vụ
	public LoaiDichVu timTheoMaLoaiDichVu(String maLoaiDichVu) {
		LoaiDichVu loaiDichVuKetQua = null;
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "SELECT *FROM LoaiDichVu WHERE MaLoaiDichVu LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maLoaiDichVu);
				result = statement.executeQuery();
				while (result.next()) {
					String tenLoaiDichVu = result.getString(2);
					loaiDichVuKetQua = new LoaiDichVu(maLoaiDichVu, tenLoaiDichVu);
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
		return loaiDichVuKetQua;

	}

	// TÌm theo ten loai dich vu
	public LoaiDichVu timTheoTenLoaiDichVu(String tenLoaiDichVu) {
		LoaiDichVu loaiDichVuKetQua = null;
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "SELECT *FROM LoaiDichVu WHERE TenLoaiDichVu LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, tenLoaiDichVu);
				result = statement.executeQuery();
				while (result.next()) {
					String maLoaiDichVu = result.getString(1);
					String tenLoaiDV = result.getString(2);
					loaiDichVuKetQua = new LoaiDichVu(maLoaiDichVu, tenLoaiDV);
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
		return loaiDichVuKetQua;
	}

	/**
	 * duyệt toàn bộ danh sách loại dịch vu
	 */
	public List<LoaiDichVu> duyetDanhSachLoaiDichVu() {
		List<LoaiDichVu> listLoaiDichVu = new ArrayList<LoaiDichVu>();
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		Statement statement = null;
		try {
			String query = "SELECT *FROM LoaiDichVu";
			statement = connect.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				String maLoaiDichVu = result.getString(1);
				String tenLoaiDV = result.getString(2);

				loaiDichVu = new LoaiDichVu(maLoaiDichVu, tenLoaiDV);
				listLoaiDichVu.add(loaiDichVu);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
			e.printStackTrace();
		} finally {
			ConnectDB.closeConnect(connect);
			ConnectDB.closeStatement(statement);
			ConnectDB.closeResultSet(result);
		}
		return listLoaiDichVu;
	}

	/**
	 * thêm loại dich vu
	 */
	public boolean themLoaiDichVu(LoaiDichVu loaiDichVu) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "INSERT LoaiDichVu (TenLoaiDichVu)\r\n" + "VALUES (?)";
				statement = connect.prepareStatement(query);
				statement.setString(1, loaiDichVu.getTenLoaiDichVu());
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
	 * Xóa loại Dich Vụ
	 */
	public boolean xoaLoaiDichVu(String maLoaiDichVu) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;

		if (connect != null) {
			try {
				String query = "DELETE LoaiDichVu WHERE MaLoaiDichVu LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maLoaiDichVu);
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
	public boolean chinhSuaLoaiDichVu(LoaiDichVu loaiDichVu) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;

		if (connect != null) {
			try {
				String query = "UPDATE LoaiDichVu\r\n" + "SET TenLoaiDichVu = ?\r\n" + "WHERE MaLoaiDichVu LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, loaiDichVu.getTenLoaiDichVu());
				statement.setString(2, loaiDichVu.getMaLoaiDichVu());
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

// dich vu
	public List<DichVuEntity> duyetDanhSach() {
		List<DichVuEntity> list = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		Statement statement = null;
		if (connect != null) {
			try {
				String query = "SELECT MaDichVu, TenDichVu, LD.MaLoaiDichVu, TenLoaiDichVu, Gia\r\n"
						+ "FROM [dbo].[DichVu] D JOIN [dbo].[LoaiDichVu] LD ON D.MaLoaiDichVu = LD.MaLoaiDichVu";
				statement = connect.createStatement();
				result = statement.executeQuery(query);
				while (result.next()) {
					String maDV = result.getString(1);
					String tenDV = result.getString(2);
					String maLoaiDichVu = result.getString(3);
					String tenloaiDV = result.getString(4);
					double gia = result.getDouble(5);
					DichVuEntity dichVuEntity = new DichVuEntity(maDV, tenDV, new LoaiDichVu(maLoaiDichVu, tenloaiDV),
							gia);
					list.add(dichVuEntity);
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
		return list;
	}

	public boolean them(DichVuEntity dichVuEntity) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;

		if (connect != null) {
			try {
				// String query = "INSERT INTO DichVu " + "([TenDV], [LoaiDV], [Gia])" + "VALUES
				// (?, ?, ?)";
				String query = "INSERT DichVu (TenDichVu, MaLoaiDichVu, Gia)\r\n" + "VALUES(?, ?, ?)";
				statement = connect.prepareStatement(query);
				statement.setString(1, dichVuEntity.getTenDichVu());
				statement.setString(2, dichVuEntity.getLoaiDichVu().getMaLoaiDichVu());
				statement.setDouble(3, dichVuEntity.getGia());
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

	public boolean xoa(String maDichVu) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;

		if (connect != null) {
			try {
				String query = "DELETE DichVu WHERE MaDichVu LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maDichVu);
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

	public boolean chinhSua(DichVuEntity dichVuEntity) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "UPDATE DichVu \r\n" + "SET TenDichVu = ?, MaLoaiDichVu = ?, Gia = ?\r\n"
						+ "where MaDichVu LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, dichVuEntity.getTenDichVu());
				statement.setString(2, dichVuEntity.getLoaiDichVu().getMaLoaiDichVu());
				statement.setDouble(3, dichVuEntity.getGia());
				statement.setString(4, dichVuEntity.getMaDichVu());
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
	 * @param loaiDV
	 * @param giaTu
	 * @param giaDen
	 * @return
	 */
	public List<DichVuEntity> timKiem(String loaiDV, Double giaTu, Double giaDen) {
		List<DichVuEntity> list = new ArrayList<DichVuEntity>();
		Connection connect = ConnectDB.getConnect();
		Statement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(
						"SELECT MaDichVu, TenDichVu, LD.MaLoaiDichVu, TenLoaiDichVu, Gia\r\n"
								+ "FROM [dbo].[DichVu] D JOIN [dbo].[LoaiDichVu] LD ON D.MaLoaiDichVu = LD.MaLoaiDichVu ");

				if (!loaiDV.equalsIgnoreCase("Tất cả") && (giaTu != null && giaDen != null)) {
					// loáº¡iDV + gia
					query.append(String.format("WHERE TenLoaiDichVu LIKE N'%%%s%%' AND Gia >= %s AND Gia <= %s", loaiDV,
							giaTu.floatValue(), giaDen.floatValue()));

				} else if (!loaiDV.equalsIgnoreCase("Tất cả") && (giaTu == null && giaDen == null)) {
					// loaiDV
					query.append(String.format("WHERE TenLoaiDichVu LIKE N'%%%s%%'", loaiDV));
				} else if (loaiDV.equalsIgnoreCase("Tất cả") && (giaTu != null && giaDen != null)) {
					// gia
					query.append(
							String.format("WHERE Gia >= %s AND Gia <= %s", giaTu.floatValue(), giaDen.floatValue()));
				}
				statement = connect.createStatement();
				result = statement.executeQuery(query.toString());
				while (result.next()) {
					String maDichVu = result.getString(1);
					String tenDichVu = result.getString(2);
					String maLoaiDichVu = result.getString(3);
					String tenloaiDichVu = result.getString(4);
					double gia = result.getDouble(5);
					DichVuEntity dichVuEntity = new DichVuEntity(maDichVu, tenDichVu,
							new LoaiDichVu(maLoaiDichVu, tenloaiDichVu), gia);
					list.add(dichVuEntity);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closeResultSet(result);
				ConnectDB.closeStatement(statement);
			}
		}

		return list;

	}

	public List<DichVuEntity> timKiemDichVu(String tenDV, String loaiDV, Double giaTu, Double giaDen) {
		List<DichVuEntity> listTimKiem = new ArrayList<DichVuEntity>();
		Connection connect = ConnectDB.getConnect();
		Statement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(
						"SELECT MaDichVu, TenDichVu, LD.MaLoaiDichVu, TenLoaiDichVu, Gia\r\n"
								+ "FROM [dbo].[DichVu] D JOIN [dbo].[LoaiDichVu] LD ON D.MaLoaiDichVu = LD.MaLoaiDichVu ");
				if (!tenDV.equals("") && !loaiDV.equalsIgnoreCase("Tất cả") && (giaTu != null && giaDen != null)) {
					// TenDV + loaiDV + gia
					query.append(String.format(
							"WHERE TenDichVu LIKE N'%%%s%%' AND TenLoaiDichVu LIKE N'%%%s%%' AND (Gia >= %s AND Gia <= %s)",
							tenDV, loaiDV, giaTu.doubleValue(), giaDen.doubleValue()));
				} else if (!tenDV.equals("") && !loaiDV.equalsIgnoreCase("Tất cả")
						&& (giaTu == null && giaDen == null)) {
					query.append(String.format("WHERE TenDichVu LIKE N'%%%s%%' AND TenLoaiDichVu LIKE N'%%%s%%'", tenDV,
							loaiDV));
					// TenDV + LoaiDV
				} else if (!tenDV.equals("") && loaiDV.equalsIgnoreCase("Tất cả")
						&& (giaTu != null && giaDen != null)) {
					query.append(String.format("WHERE TenDichVu LIKE N'%%%s%%' AND (Gia >= %s AND Gia <= %s)", tenDV,
							giaTu.doubleValue(), giaDen.doubleValue()));
					// TenDv + Gia
				} else if (tenDV.equals("") && !loaiDV.equalsIgnoreCase("Tất cả")
						&& (giaTu != null && giaDen != null)) {
					query.append(String.format("WHERE TenLoaiDichVu LIKE N'%%%s%%' AND (Gia >= %s AND Gia <= %s)",
							loaiDV, giaTu.doubleValue(), giaDen.doubleValue()));
					// loaidv + gia
				} else if (tenDV.equals("") && !loaiDV.equalsIgnoreCase("Tất cả")
						&& (giaTu == null && giaDen == null)) {
					query.append(String.format("WHERE TenLoaiDichVu LIKE N'%%%s%%'", loaiDV));
					// loaiDV
				} else if (tenDV.equals("") && loaiDV.equalsIgnoreCase("Tất cả") && (giaTu != null && giaDen != null)) {
					query.append(
							String.format("WHERE Gia >= %s AND Gia <= %s", giaTu.doubleValue(), giaDen.doubleValue()));
					// gia
				} else if (!tenDV.equals("") && loaiDV.equalsIgnoreCase("Tất cả")
						&& (giaTu == null && giaDen == null)) {
					// ten
					query.append(String.format("WHERE TenDichVu LIKE N'%%%s%%'", tenDV));
				}

				statement = connect.createStatement();
				result = statement.executeQuery(query.toString());
				while (result.next()) {
					String maDichVu = result.getString(1);
					String tenDichVu = result.getString(2);
					String maLoaiDichVu = result.getString(3);
					String tenloaiDichVu = result.getString(4);
					double gia = result.getDouble(5);
					DichVuEntity dichVuEntity = new DichVuEntity(maDichVu, tenDichVu,
							new LoaiDichVu(maLoaiDichVu, tenloaiDichVu), gia);
					listTimKiem.add(dichVuEntity);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closeResultSet(result);
				ConnectDB.closeStatement(statement);
			}
		}
		return listTimKiem;
	}

	public DichVuEntity timTheoMa(String maDichVu) {
		DichVuEntity dichVuEntity = null;
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				String query = "SELECT MaDichVu, TenDichVu, LDV.MaLoaiDichVu, TenLoaiDichVu, Gia \r\n"
						+ "FROM DichVu DV JOIN LoaiDichVu LDV ON DV.MaLoaiDichVu = LDV.MaLoaiDichVu WHERE MaDichVu LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maDichVu);
				result = statement.executeQuery();
				while (result.next()) {
					String tenDichVu = result.getString(2);
					String maLoaiDichVu = result.getString(3);
					String tenloaiDichVu = result.getString(4);
					double gia = result.getDouble(5);
					LoaiDichVu loaDichVu = new LoaiDichVu(maLoaiDichVu, tenloaiDichVu);
					dichVuEntity = new DichVuEntity(maDichVu, tenDichVu, loaDichVu, gia);
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
		return dichVuEntity;
	}
}
