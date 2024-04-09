package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entities.NhanVienEntity;
import util.ConnectDB;
import util.PasswordHasher;

public class QuanLyNhanVienDAO {
	public QuanLyNhanVienDAO() {

	}

	public List<NhanVienEntity> duyetDanhSach() {
		List<NhanVienEntity> listNhanVien = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		Statement statemant = null;

		if (connect != null) {
			try {
				String query = "SELECT * FROM NhanVien";
				statemant = connect.createStatement();
				result = statemant.executeQuery(query);
				while (result.next()) {
					String maNhanVien = result.getString(1);
					String hoTen = result.getString(2);
					String soDienThoai = result.getString(3);
					String email = result.getString(4);
					String CCCD = result.getString(5);
					String password = result.getString(6);
					int namSinh = result.getInt(7);
					double mucLuong = result.getDouble(8);
					String ChucVu = result.getString(9);
					boolean trangThai = false;
					if (result.getString(10).equalsIgnoreCase("Đang làm việc")) {
						trangThai = true;
					}
					NhanVienEntity nhanVienEntity = new NhanVienEntity(maNhanVien, hoTen, soDienThoai, email, CCCD,
							password, namSinh, mucLuong, ChucVu, trangThai);
					listNhanVien.add(nhanVienEntity);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closeStatement(statemant);
				ConnectDB.closeResultSet(result);
			}
		}
		return listNhanVien;
	}

	public List<NhanVienEntity> duyetDanhSachNhanVienDangLamViec() {
		List<NhanVienEntity> listNhanVien = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		Statement statemant = null;

		if (connect != null) {
			try {
				String query = "SELECT * FROM NhanVien WHERE TrangThai = N'Đang làm việc'";
				statemant = connect.createStatement();
				result = statemant.executeQuery(query);
				while (result.next()) {
					String maNhanVien = result.getString(1);
					String hoTen = result.getString(2);
					String soDienThoai = result.getString(3);
					String email = result.getString(4);
					String CCCD = result.getString(5);
					String password = result.getString(6);
					int namSinh = result.getInt(7);
					double mucLuong = result.getDouble(8);
					String ChucVu = result.getString(9);
					boolean trangThai = false;
					if (result.getString(10).equalsIgnoreCase("Đang làm việc")) {
						trangThai = true;
					}
					NhanVienEntity nhanVienEntity = new NhanVienEntity(maNhanVien, hoTen, soDienThoai, email, CCCD,
							password, namSinh, mucLuong, ChucVu, trangThai);
					listNhanVien.add(nhanVienEntity);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closeStatement(statemant);
				ConnectDB.closeResultSet(result);
			}
		}
		return listNhanVien;
	}

	public NhanVienEntity timTheoMa(String maNhanvien) {
		NhanVienEntity nhanVienEntity = null;
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		PreparedStatement statemant = null;

		if (connect != null) {
			try {
				String query = "SELECT * FROM NhanVien WHERE MaNhanVien LIKE ?";
				statemant = connect.prepareStatement(query);
				statemant.setString(1, maNhanvien);
				result = statemant.executeQuery();
				while (result.next()) {
					String maNhanVien = result.getString(1);
					String hoTen = result.getString(2);
					String soDienThoai = result.getString(3);
					String email = result.getString(4);
					String CCCD = result.getString(5);
					String password = result.getString(6);
					int namSinh = result.getInt(7);
					double mucLuong = result.getDouble(8);
					String ChucVu = result.getString(9);
					boolean trangThai = false;
					if (result.getString(10).equalsIgnoreCase("Đang làm việc")) {
						trangThai = true;
					}
					nhanVienEntity = new NhanVienEntity(maNhanVien, hoTen, soDienThoai, email, CCCD, password, namSinh,
							mucLuong, ChucVu, trangThai);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closeStatement(statemant);
				ConnectDB.closeResultSet(result);
			}
		}
		return nhanVienEntity;
	}

	public NhanVienEntity timTheoTenVaSoDienThoai(String hoTen, String soDienThoai) {
		NhanVienEntity nhanVienEntity = null;
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		PreparedStatement statemant = null;

		if (connect != null) {
			try {
				String query = "SELECT * FROM NhanVien WHERE HoTen LIKE ? AND SoDienThoai = ?";
				statemant = connect.prepareStatement(query);
				statemant.setString(1, hoTen);
				statemant.setString(2, soDienThoai);
				result = statemant.executeQuery();
				while (result.next()) {
					String maNhanVien = result.getString(1);
					String hoTenNhanVien = result.getString(2);
					String sdt = result.getString(3);
					String email = result.getString(4);
					String CCCD = result.getString(5);
					String password = result.getString(6);
					int namSinh = result.getInt(7);
					double mucLuong = result.getDouble(8);
					String ChucVu = result.getString(9);
					boolean trangThai = false;
					if (result.getString(10).equalsIgnoreCase("Đang làm việc")) {
						trangThai = true;
					}
					nhanVienEntity = new NhanVienEntity(maNhanVien, hoTenNhanVien, sdt, email, CCCD, password, namSinh,
							mucLuong, ChucVu, trangThai);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closeStatement(statemant);
				ConnectDB.closeResultSet(result);
			}
		}
		return nhanVienEntity;
	}

	public boolean them(NhanVienEntity nhanVienEntity) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;

		if (connect != null) {
			try {
				String query = "INSERT INTO [dbo].[NhanVien] "
						+ "([HoTen], [SoDienThoai], [Email], [CCCD], [Password], [NamSinh], [MucLuong], [ChucVu], [TrangThai])"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				String trangThai = "Đã nghỉ";
				statement = connect.prepareStatement(query);
				statement.setString(1, nhanVienEntity.getHoTen());
				statement.setString(2, nhanVienEntity.getSoDienThoai());
				statement.setString(3, nhanVienEntity.getEmail());
				statement.setString(4, nhanVienEntity.getcCCD());
				statement.setString(5, nhanVienEntity.getPassword());
				statement.setInt(6, nhanVienEntity.getNamSinh());
				statement.setDouble(7, nhanVienEntity.getMucLuong());
				statement.setString(8, nhanVienEntity.getChucVu());
				if (nhanVienEntity.isTrangThai()) {
					trangThai = "Đang làm việc";
				}
				statement.setString(9, trangThai);
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

	public NhanVienEntity timTheoSoDienThoaiVaPassword(String soDienThoai, String password) {
		NhanVienEntity nhanVienEntity = null;
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				String query = "SELECT * FROM NhanVien\r\n"
						+ "WHERE SoDienThoai = ? AND Password = ? AND TrangThai = N'Đang làm việc'";
				statement = connect.prepareStatement(query);
				statement.setString(1, soDienThoai);
				statement.setString(2, PasswordHasher.hashPassword(password));
				result = statement.executeQuery();
				while (result.next()) {
					String maNhanVien = result.getString(1);
					String hoTen = result.getString(2);
					String soDienThoai1 = result.getString(3);
					String email = result.getString(4);
					String CCCD = result.getString(5);
					String password1 = result.getString(6);
					int namSinh = result.getInt(7);
					double mucLuong = result.getDouble(8);
					String ChucVu = result.getString(9);
					boolean trangThai = false;
					if (result.getString(10).equalsIgnoreCase("Đang làm việc")) {
						trangThai = true;
					}
					nhanVienEntity = new NhanVienEntity(maNhanVien, hoTen, soDienThoai1, email, CCCD, password1,
							namSinh, mucLuong, ChucVu, trangThai);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statement);
			}
		}

		return nhanVienEntity;
	}

	public boolean chinhSua(NhanVienEntity nhanVienEntity) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;

		if (connect != null) {
			try {
				String query = "UPDATE [dbo].[NhanVien]\r\n"
						+ "SET [SoDienThoai] = ?, [Email] = ?, [MucLuong] = ?, [ChucVu] = ?, [TrangThai] = ?\r\n"
						+ "WHERE [MaNhanVien] LIKE ?";
				String trangThai = "Đã nghỉ";
				if (nhanVienEntity.isTrangThai()) {
					trangThai = "Đang làm việc";
				}
				statement = connect.prepareStatement(query);
				statement.setString(1, nhanVienEntity.getSoDienThoai());
				statement.setString(2, nhanVienEntity.getEmail());
				statement.setDouble(3, nhanVienEntity.getMucLuong());
				statement.setString(4, nhanVienEntity.getChucVu());
				statement.setString(5, trangThai);
				statement.setString(6, nhanVienEntity.getMaNhanVien());
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

	public List<NhanVienEntity> timKiem(String hoTen, String soDienThoai, String chucVu, String trangThai) {
		List<NhanVienEntity> listNhanvien = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		Statement statement = null;
		ResultSet result = null;

		if (connect != null) {

			try {
				StringBuilder query = new StringBuilder("SELECT * FROM NhanVien ");
				if (!hoTen.equals("") && !soDienThoai.equals("") && !chucVu.equals("Tất cả")
						&& !trangThai.equals("Tất cả")) {
					// HoTen + SoDienThoai + ChucVu + trangthai
					query.append(String.format(
							"WHERE HoTen LIKE N'%%%s%%' AND SoDienThoai LIKE '%s' AND ChucVu LIKE N'%s' AND TrangThai LIKE N'%s'",
							hoTen, soDienThoai, chucVu, trangThai));
				} else if (!hoTen.equals("") && !soDienThoai.equals("") && !chucVu.equals("Tất cả")
						&& trangThai.equals("Tất cả")) {
					// Hoten + SoDienThoai + ChucVu
					query.append(
							String.format("WHERE HoTen LIKE N'%%%s%%' AND SoDienThoai LIKE '%s' AND ChucVu LIKE N'%s'",
									hoTen, soDienThoai, chucVu));
				} else if (!hoTen.equals("") && !soDienThoai.equals("") && chucVu.equals("Tất cả")
						&& !trangThai.equals("Tất cả")) {
					// hoten + SoDienThoai + trangthai
					query.append(String.format(
							"WHERE HoTen LIKE N'%%%s%%' AND SoDienThoai LIKE '%s' AND TrangThai LIKE N'%s'", hoTen,
							soDienThoai, trangThai));
				} else if (!hoTen.equals("") && soDienThoai.equals("") && !chucVu.equals("Tất cả")
						&& !trangThai.equals("Tất cả")) {
					// hoten + chucvu + trangthai
					query.append(
							String.format("WHERE HoTen LIKE N'%%%s%%' AND ChucVu LIKE N'%s' AND TrangThai LIKE N'%s'",
									hoTen, chucVu, trangThai));
				} else if (hoTen.equals("") && !soDienThoai.equals("") && !chucVu.equals("Tất cả")
						&& !trangThai.equals("Tất cả")) {
					// SoDienThoai + chucvu + trangthai
					query.append(
							String.format("WHERE SoDienThoai LIKE '%s' AND ChucVu LIKE N'%s' AND TrangThai LIKE N'%s'",
									soDienThoai, chucVu, trangThai));
				} else if (!hoTen.equals("") && !soDienThoai.equals("") && chucVu.equals("Tất cả")
						&& trangThai.equals("Tất cả")) {
					// hoten + SoDienThoai
					query.append(
							String.format("WHERE HoTen LIKE N'%%%s%%' AND SoDienThoai LIKE '%s'", hoTen, soDienThoai));
				} else if (!hoTen.equals("") && soDienThoai.equals("") && !chucVu.equals("Tất cả")
						&& trangThai.equals("Tất cả")) {
					// hoten + chucvu
					query.append(String.format("WHERE HoTen LIKE N'%%%s%%' AND ChucVu LIKE N'%s' ", hoTen, chucVu));
				} else if (!hoTen.equals("") && soDienThoai.equals("") && chucVu.equals("Tất cả")
						&& !trangThai.equals("Tất cả")) {
					// hoten + trangthai
					query.append(
							String.format("WHERE HoTen LIKE N'%%%s%%' AND TrangThai LIKE N'%s'", hoTen, trangThai));
				} else if (hoTen.equals("") && !soDienThoai.equals("") && !chucVu.equals("Tất cả")
						&& trangThai.equals("Tất cả")) {
					// SoDienThoai + chucvu
					query.append(
							String.format("WHERE SoDienThoai LIKE '%s' AND ChucVu LIKE N'%s' ", soDienThoai, chucVu));
				} else if (hoTen.equals("") && !soDienThoai.equals("") && chucVu.equals("Tất cả")
						&& !trangThai.equals("Tất cả")) {
					// SoDienThoai + trangthai
					query.append(String.format("WHERE SoDienThoai LIKE '%s' AND TrangThai LIKE N'%s'", soDienThoai,
							trangThai));
				} else if (hoTen.equals("") && soDienThoai.equals("") && !chucVu.equals("Tất cả")
						&& !trangThai.equals("Tất cả")) {
					// chucvu + trangthai
					query.append(String.format("WHERE ChucVu LIKE N'%s' AND TrangThai LIKE N'%s'", chucVu, trangThai));
				} else if (!hoTen.equals("") && soDienThoai.equals("") && chucVu.equals("Tất cả")
						&& trangThai.equals("Tất cả")) {
					// hoten
					query.append(String.format("WHERE HoTen LIKE N'%%%s%%'", hoTen));
				} else if (hoTen.equals("") && !soDienThoai.equals("") && chucVu.equals("Tất cả")
						&& trangThai.equals("Tất cả")) {
					// SoDienThoai
					query.append(String.format("WHERE SoDienThoai LIKE '%s'", soDienThoai));
				} else if (hoTen.equals("") && soDienThoai.equals("") && !chucVu.equals("Tất cả")
						&& trangThai.equals("Tất cả")) {
					// chucvu
					query.append(String.format("WHERE ChucVu LIKE N'%s'", chucVu));
				} else if (hoTen.equals("") && soDienThoai.equals("") && chucVu.equals("Tất cả")
						&& !trangThai.equals("Tất cả")) {
					// trangthai
					query.append(String.format("WHERE TrangThai LIKE N'%s'", trangThai));
				}
				statement = connect.createStatement();
				result = statement.executeQuery(query.toString());
				while (result.next()) {
					String maNhanVien = result.getString(1);
					String hoten = result.getString(2);
					String SoDienThoai = result.getString(3);
					String email = result.getString(4);
					String CCCD = result.getString(5);
					String password = result.getString(6);
					int namSinh = result.getInt(7);
					double mucLuong = result.getDouble(8);
					String ChucVu = result.getString(9);
					boolean trangthai = false;
					if (result.getString(10).equalsIgnoreCase("Đang làm việc")) {
						trangthai = true;
					}
					NhanVienEntity nhanVienEntity = new NhanVienEntity(maNhanVien, hoten, SoDienThoai, email, CCCD,
							password, namSinh, mucLuong, ChucVu, trangthai);
					listNhanvien.add(nhanVienEntity);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi Cơ sở dữ liệu");
				e.printStackTrace();
			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closeStatement(statement);
				ConnectDB.closeResultSet(result);
			}
		}

		return listNhanvien;
	}

	/***** ĐỔI MẬT KHẨU *****/
	public boolean doiMatKhau(String matKhauMoi, String soDienThoai) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;

		String query = "UPDATE NhanVien\r\n" + "SET Password = ?\r\n" + "WHERE SoDienThoai = ?";
		if (connect != null) {
			try {
				statement = connect.prepareStatement(query);
				matKhauMoi = PasswordHasher.hashPassword(matKhauMoi);
				statement.setString(1, matKhauMoi);
				statement.setString(2, soDienThoai);
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
}
