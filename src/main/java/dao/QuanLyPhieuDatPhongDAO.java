package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import entities.ChiTietPhieuDatPhongEntity;
import entities.PhieuDatPhongEntity;
import util.ConnectDB;

public class QuanLyPhieuDatPhongDAO {

	public QuanLyPhieuDatPhongDAO() {

	}

	/*********************************************************
	 * CHI TIẾT PHIẾU ĐẶT PHÒNG
	 *********************************************************/

	public List<ChiTietPhieuDatPhongEntity> duyetChiTietPhieuDatPhongChuaThanhToan() {
		List<ChiTietPhieuDatPhongEntity> listChiTietPhieuDatPhong = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		Statement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				String query = "SELECT * FROM ChiTietPhieuDatPhong CTPDP\r\n"
						+ "WHERE EXISTS (SELECT * FROM ChiTietDatPhong CTDP JOIN ChiTietHoaDon CTHD\r\n"
						+ "	ON CTDP.MaChiTietDatPhong = CTHD.MaChiTietDatPhong JOIN Phong P\r\n"
						+ "	ON CTDP.MaPhong = P.MaPhong\r\n"
						+ "	WHERE CTDP.MaChiTietDatPhong = CTPDP.MaChiTietDatPhong AND MaHoaDon IS NULL)";
				statement = connect.createStatement();
				result = statement.executeQuery(query);
				while (result.next()) {
					String maChiTietPhieuDatPhong = result.getString(1);
					String maPhieuDatPhong = result.getString(2);
					String maChiTietDatPhong = result.getString(3);
					ChiTietPhieuDatPhongEntity chiTietPhieuDatPhongEntity = new ChiTietPhieuDatPhongEntity(
							maChiTietPhieuDatPhong, maPhieuDatPhong, maChiTietDatPhong);
					listChiTietPhieuDatPhong.add(chiTietPhieuDatPhongEntity);
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
		return listChiTietPhieuDatPhong;
	}

	public boolean themChiTietPhieuDatPhong(ChiTietPhieuDatPhongEntity chiTietPhieuDatPhongEntity) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;

		if (connect != null) {
			try {
				String query = "INSERT INTO ChiTietPhieuDatPhong (MaPhieuDatPhong, MaChiTietDatPhong)\r\n"
						+ "VALUES (?, ?)";
				statement = connect.prepareStatement(query);
				statement.setString(1, chiTietPhieuDatPhongEntity.getMaPhieuDatPhong());
				statement.setString(2, chiTietPhieuDatPhongEntity.getMaChiTietDatPhong());
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

	public ChiTietPhieuDatPhongEntity timChiTietPhieuDatPhongTheoMa(String maChiTietPhieuDatPhong) {
		ChiTietPhieuDatPhongEntity chiTietPhieuDatPhongEntity = null;
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				String query = "SELECT * FROM ChiTietPhieuDatPhong WHERE MaChiTietPhieuDatPhong LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maChiTietPhieuDatPhong);
				result = statement.executeQuery();
				while (result.next()) {
					String maPhieuDatPhong = result.getString(2);
					String maChiTietDatPhong = result.getString(3);
					chiTietPhieuDatPhongEntity = new ChiTietPhieuDatPhongEntity(maChiTietPhieuDatPhong, maPhieuDatPhong,
							maChiTietDatPhong);
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
		return chiTietPhieuDatPhongEntity;
	}

	public List<ChiTietPhieuDatPhongEntity> timChiTietPhieuDatPhongTheoSoPhongVaMa(int soPhong,
			String maPhieuDatPhong) {
		List<ChiTietPhieuDatPhongEntity> listChiTietPhieuDatPhong = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		Statement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(String.format("SELECT * FROM ChiTietPhieuDatPhong CTPDP\r\n"
						+ "WHERE MaPhieuDatPhong LIKE '%%%s%%' \r\n"
						+ "	AND EXISTS (SELECT * FROM ChiTietDatPhong CTDP JOIN ChiTietHoaDon CTHD\r\n"
						+ "		ON CTDP.MaChiTietDatPhong = CTHD.MaChiTietDatPhong JOIN Phong P\r\n"
						+ "		ON CTDP.MaPhong = P.MaPhong\r\n"
						+ "		WHERE CTDP.MaChiTietDatPhong = CTPDP.MaChiTietDatPhong AND MaHoaDon IS NULL AND SoPhong LIKE '%%%s%%')",
						maPhieuDatPhong, soPhong));
				statement = connect.createStatement();
				result = statement.executeQuery(query.toString());
				while (result.next()) {
					String maChiTietPhieuDatPhong = result.getString(1);
					String maChiTietDatPhong = result.getString(3);
					ChiTietPhieuDatPhongEntity chiTietPhieuDatPhongEntity = new ChiTietPhieuDatPhongEntity(
							maChiTietPhieuDatPhong, maPhieuDatPhong, maChiTietDatPhong);
					listChiTietPhieuDatPhong.add(chiTietPhieuDatPhongEntity);
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
		return listChiTietPhieuDatPhong;
	}

	public List<ChiTietPhieuDatPhongEntity> timChiTietPhieuDatPhongTheoMaPhieuDatPhong(String maPhieuDatPhong) {
		List<ChiTietPhieuDatPhongEntity> listChiTietPhieuDatPhong = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				String query = "SELECT * FROM ChiTietPhieuDatPhong CTPDP\r\n" + "WHERE MaPhieuDatPhong LIKE ? \r\n"
						+ "	AND EXISTS (SELECT * FROM ChiTietDatPhong CTDP JOIN ChiTietHoaDon CTHD\r\n"
						+ "	ON CTDP.MaChiTietDatPhong = CTHD.MaChiTietDatPhong\r\n"
						+ "	WHERE CTDP.MaChiTietDatPhong = CTPDP.MaChiTietDatPhong AND MaHoaDon IS NULL)";
				statement = connect.prepareStatement(query);
				statement.setString(1, maPhieuDatPhong);
				result = statement.executeQuery();
				while (result.next()) {
					String maChiTietPhieuDatPhong = result.getString(1);
					String maChiTietDatPhong = result.getString(3);
					ChiTietPhieuDatPhongEntity chiTietPhieuDatPhongEntity = new ChiTietPhieuDatPhongEntity(
							maChiTietPhieuDatPhong, maPhieuDatPhong, maChiTietDatPhong);
					listChiTietPhieuDatPhong.add(chiTietPhieuDatPhongEntity);
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
		return listChiTietPhieuDatPhong;
	}

	public boolean xoaChiTietPhieuDatPhong(String maChiTietDatPhong) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "DELETE FROM ChiTietPhieuDatPhong WHERE MaChiTietDatPhong LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maChiTietDatPhong);
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

	/*********************************************************
	 * PHIẾU ĐẶT PHÒNG
	 *********************************************************/

	public List<PhieuDatPhongEntity> duyetDanhSachDatPhong() {
		List<PhieuDatPhongEntity> listPhieuDatPhong = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		Statement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				String query = "SELECT * FROM PhieuDatPhong PDP\r\n"
						+ "WHERE EXISTS (SELECT * FROM ChiTietPhieuDatPhong CTPDP JOIN ChiTietDatPhong CTDP\r\n"
						+ "ON CTPDP.MaChiTietDatPhong = CTDP.MaChiTietDatPhong JOIN Phong P\r\n"
						+ "ON P.MaPhong = CTDP.MaPhong JOIN ChiTietHoaDon CTHD\r\n"
						+ "ON CTDP.MaChiTietDatPhong = CTHD.MaChiTietDatPhong\r\n"
						+ "WHERE MaHoaDon IS NULL AND CTPDP.MaPhieuDatPhong = PDP.MaPhieuDatPhong)";
				statement = connect.createStatement();
				result = statement.executeQuery(query);
				while (result.next()) {
					String maPhieuDatPhong = result.getString(1);
					String maKhachHang = result.getString(2);
					PhieuDatPhongEntity phieuDatPhongEntity = new PhieuDatPhongEntity(maPhieuDatPhong, maKhachHang);
					listPhieuDatPhong.add(phieuDatPhongEntity);
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
		return listPhieuDatPhong;
	}

	public boolean themPhieuDatPhong(String maKhachHang) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;

		if (connect != null) {
			try {
				String query = "INSERT INTO PhieuDatPhong (MaKhachHang)\r\n" + "VALUES (?)";
				statement = connect.prepareStatement(query);
				statement.setString(1, maKhachHang);
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

	public PhieuDatPhongEntity timPhieuDatPhongTheoMa(String maPhieuDatPhong) {
		PhieuDatPhongEntity phieuDatPhongEntity = null;
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		ResultSet result = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM PhieuDatPhong WHERE MaPhieuDatPhong LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maPhieuDatPhong);
				result = statement.executeQuery();
				while (result.next()) {
					String maKhachHang = result.getString(2);
					phieuDatPhongEntity = new PhieuDatPhongEntity(maPhieuDatPhong, maKhachHang);
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

		return phieuDatPhongEntity;
	}

	public List<PhieuDatPhongEntity> timPhieuDatPhongTruocTheoSoPhongVaKhachHang(int soPhong, String khachHang) {
		List<PhieuDatPhongEntity> listPhieuDatPhong = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		Statement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(
						"SELECT PDP.MaPhieuDatPhong, PDP.MaKhachHang FROM PhieuDatPhong PDP JOIN KhachHang KH\r\n"
								+ "ON PDP.MaKhachHang = KH.MaKhachHang\r\n"
								+ "WHERE EXISTS (SELECT * FROM ChiTietPhieuDatPhong CTPDP JOIN ChiTietDatPhong CTDP\r\n"
								+ "ON CTPDP.MaChiTietDatPhong = CTDP.MaChiTietDatPhong JOIN Phong P\r\n"
								+ "ON P.MaPhong = CTDP.MaPhong JOIN ChiTietHoaDon CTHD\r\n"
								+ "ON CTDP.MaChiTietDatPhong = CTHD.MaChiTietDatPhong\r\n"
								+ "WHERE MaHoaDon IS NULL AND CTPDP.MaPhieuDatPhong = PDP.MaPhieuDatPhong");
				if (soPhong >= 0 && !khachHang.equals("")) {
					query.append(
							String.format(" AND SoPhong LIKE '%%%s%%') AND HoTen LIKE N'%%%s%%'", soPhong, khachHang));
				} else if (soPhong >= 0 && khachHang.equals("")) {
					query.append(String.format(" AND SoPhong LIKE '%%%s%%')", soPhong));
				} else if (soPhong < 0 && !khachHang.equals("")) {
					query.append(String.format(") AND HoTen LIKE N'%%%s%%'", khachHang));
				}
				statement = connect.createStatement();
				result = statement.executeQuery(query.toString());
				while (result.next()) {
					String maPhieuDatPhong = result.getString(1);
					String maKhachHang = result.getString(2);
					PhieuDatPhongEntity phieuDatPhongEntity = new PhieuDatPhongEntity(maPhieuDatPhong, maKhachHang);
					listPhieuDatPhong.add(phieuDatPhongEntity);
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
		return listPhieuDatPhong;
	}

	public PhieuDatPhongEntity timPhieuDatPhongVuaTao() {
		PhieuDatPhongEntity phieuDatPhongEntity = null;
		Connection connect = ConnectDB.getConnect();
		Statement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				String query = "select TOP 1 * from PhieuDatPhong ORDER BY MaPhieuDatPhong DESC";
				statement = connect.createStatement();
				result = statement.executeQuery(query);
				while (result.next()) {
					String maPhieuDatPhong = result.getString(1);
					String maKhachHang = result.getString(2);
					phieuDatPhongEntity = new PhieuDatPhongEntity(maPhieuDatPhong, maKhachHang);
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
		return phieuDatPhongEntity;
	}

	public boolean xoaPhieuDatPhong(String maPhieuDatPhong) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "DELETE FROM PhieuDatPhong WHERE MaPhieuDatPhong LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maPhieuDatPhong);
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

	public List<PhieuDatPhongEntity> duyetDanhSachPhieuDatPhongChuaThanhToan() {
		List<PhieuDatPhongEntity> listPhieuDatPhong = new ArrayList<PhieuDatPhongEntity>();
		Connection connect = ConnectDB.getConnect();
		Statement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				String query = "select *from PhieuDatPhong pdp\r\n"
						+ "where exists (select *from ChiTietPhieuDatPhong ctpdp \r\n"
						+ "join ChiTietDatPhong ctdp on ctdp.MaChiTietDatPhong = ctpdp.MaChiTietDatPhong\r\n"
						+ "join ChiTietHoaDon cthd on cthd.MaChiTietDatPhong = ctdp.MaChiTietDatPhong\r\n"
						+ "where pdp.MaPhieuDatPhong = ctpdp.MaPhieuDatPhong and cthd.MaHoaDon is null) ";
				statement = connect.createStatement();
				result = statement.executeQuery(query);
				while (result.next()) {
					String maPhieuDatPhong = result.getString(1);
					String maKhachHang = result.getString(2);
					PhieuDatPhongEntity phieuDatPhongEntity = new PhieuDatPhongEntity(maPhieuDatPhong, maKhachHang);
					listPhieuDatPhong.add(phieuDatPhongEntity);
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
		return listPhieuDatPhong;
	}

	public List<ChiTietPhieuDatPhongEntity> duyetDanhSachChiTietPhieuDatPhongTheoPhieuDatPhong(String maPhieuDatPhong) {
		List<ChiTietPhieuDatPhongEntity> listChiTietPhieuDatPhong = new ArrayList<ChiTietPhieuDatPhongEntity>();
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "select *from ChiTietPhieuDatPhong\r\n" + "where MaPhieuDatPhong = ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maPhieuDatPhong);
				result = statement.executeQuery();
				while (result.next()) {
					String maChiTietPhieuDatPhong = result.getString(1);
					String maChiTietDatPhong = result.getString(3);

					ChiTietPhieuDatPhongEntity chiTietPhieuDatPhongEntity = new ChiTietPhieuDatPhongEntity(
							maChiTietPhieuDatPhong, maPhieuDatPhong, maChiTietDatPhong);
					listChiTietPhieuDatPhong.add(chiTietPhieuDatPhongEntity);

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
		return listChiTietPhieuDatPhong;
	}
}
