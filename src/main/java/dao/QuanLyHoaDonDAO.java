package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entities.ChiTietDatPhongEntity;
import entities.ChiTietDichVuEntity;
import entities.ChiTietHoaDonEntity;
import entities.DichVuEntity;
import entities.PhongEntity;
import util.ConnectDB;
import util.DateFormatter;
import util.TimeFormatter;

public class QuanLyHoaDonDAO {
	private QuanLyPhongDAO quanLyPhongDAO = new QuanLyPhongDAO();
	private QuanLyDichVuDAO quanLyDichVuDAO = new QuanLyDichVuDAO();

	public QuanLyHoaDonDAO() {

	}

	/*********************************************************
	 * CHI TIẾT HÓA ĐƠN
	 *********************************************************/

	public boolean themChiTietHoaDon(String maChiTietDatPhong) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "INSERT INTO ChiTietHoaDon (MaChiTietDatPhong)\r\n" + "VALUES (?)";
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

	public boolean xoaChiTietHoaDon(String maChiTietDatPhong) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "DELETE FROM ChiTietHoaDon WHERE MaChiTietDatPhong LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maChiTietDatPhong);
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

	public ChiTietHoaDonEntity timChiTietHoaDonTheoChiTietDatPhong(ChiTietDatPhongEntity chiTietDatPhongEntity) {
		ChiTietHoaDonEntity chiTietHoaDonEntity = null;
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		ResultSet result = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM ChiTietHoaDon WHERE MaChiTietDatPhong LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, chiTietDatPhongEntity.getMaChiTietDatPhong());
				result = statement.executeQuery();
				while (result.next()) {
					String maChiTietHoaDon = result.getString(1);
					chiTietHoaDonEntity = new ChiTietHoaDonEntity(maChiTietHoaDon, chiTietDatPhongEntity);
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

		return chiTietHoaDonEntity;
	}

	public List<ChiTietHoaDonEntity> duyetDanhSachChiTietHoaDonTheoMaHoaDon(String maHoaDon) {
		List<ChiTietHoaDonEntity> listChiTietHoaDon = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				String query = "SELECT * FROM ChiTietHoaDon WHERE MaHoaDon LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maHoaDon);
				result = statement.executeQuery();
				while (result.next()) {
					String maChiTietHoaDon = result.getString(1);
					String maChiTietDatPhong = result.getString(3);
					ChiTietDatPhongEntity chiTietDatPhongEntity = timChiTietDatPhongTheoMa(maChiTietDatPhong);
					ChiTietHoaDonEntity chiTietHoaDonEntity = new ChiTietHoaDonEntity(maChiTietHoaDon, maHoaDon,
							chiTietDatPhongEntity);
					listChiTietHoaDon.add(chiTietHoaDonEntity);
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
		return listChiTietHoaDon;
	}

	/*********************************************************
	 * CHI TIẾT ĐẶT PHÒNG
	 *********************************************************/
	public boolean themChiTietDatPhong(ChiTietDatPhongEntity chiTietDatPhongEntity) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "INSERT INTO ChiTietDatPhong (MaPhong, NgayDatPhong, GioNhanPhong, GioTraPhong)\r\n"
						+ "VALUES (?, ?, ?, ?)";
				statement = connect.prepareStatement(query);
				statement.setString(1, chiTietDatPhongEntity.getPhong().getMaPhong());
				statement.setDate(2, Date.valueOf(chiTietDatPhongEntity.getNgayNhanPhong()));
				statement.setTime(3, Time.valueOf(chiTietDatPhongEntity.getGioNhanPhong()));
				statement.setTime(4, Time.valueOf(chiTietDatPhongEntity.getGioTraPhong()));
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

	public boolean xoaChiTietDatPhong(String maChiTietDatPhong) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "DELETE FROM ChiTietDatPhong WHERE MaChiTietDatPhong LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maChiTietDatPhong);
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

	public ChiTietDatPhongEntity timChiTietDatPhongTheoMa(String maChiTietDatPhong) {
		ChiTietDatPhongEntity chiTietPhieuDatPhongEntity = null;
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				String query = "SELECT * FROM ChiTietDatPhong WHERE MaChiTietDatPhong LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maChiTietDatPhong);
				result = statement.executeQuery();
				while (result.next()) {
					PhongEntity phongEntity = quanLyPhongDAO.timTheoMa(result.getString(2));
					LocalDate ngayNhanPhong = result.getDate(3).toLocalDate();
					LocalTime gioNhanPhong = result.getTime(4).toLocalTime();
					LocalTime gioTraPhong = result.getTime(5).toLocalTime();
					chiTietPhieuDatPhongEntity = new ChiTietDatPhongEntity(maChiTietDatPhong, phongEntity, gioNhanPhong,
							gioTraPhong, ngayNhanPhong);
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

	public List<ChiTietDatPhongEntity> timChiTietDatPhongChuaThanhToanTheoPhong(PhongEntity phongEntity) {
		List<ChiTietDatPhongEntity> listChiTietDatPhong = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		ResultSet result = null;
		if (connect != null) {
			try {
				String query = "SELECT MaChiTietDatPhong FROM ChiTietHoaDon WHERE MaHoaDon IS NULL";
				statement = connect.prepareStatement(query);
				result = statement.executeQuery();
				while (result.next()) {
					ChiTietDatPhongEntity chiTietDatPhongEntity = timChiTietDatPhongTheoMa(result.getString(1));
					if (chiTietDatPhongEntity.getPhong().getMaPhong().equals(phongEntity.getMaPhong()))
						listChiTietDatPhong.add(chiTietDatPhongEntity);
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
		return listChiTietDatPhong;
	}

	public List<ChiTietDatPhongEntity> timChiTietDatPhongChuaThanhToan() {
		List<ChiTietDatPhongEntity> listChiTietDatPhong = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		ResultSet result = null;
		if (connect != null) {
			try {
				String query = "SELECT MaChiTietDatPhong FROM ChiTietHoaDon WHERE MaHoaDon IS NULL";
				statement = connect.prepareStatement(query);
				result = statement.executeQuery();
				while (result.next()) {
					ChiTietDatPhongEntity chiTietDatPhongEntity = timChiTietDatPhongTheoMa(result.getString(1));
					listChiTietDatPhong.add(chiTietDatPhongEntity);
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
		return listChiTietDatPhong;
	}

	public ChiTietDatPhongEntity timChiTietDatPhongVuaTao() {
		ChiTietDatPhongEntity chiTietDatPhongEntity = null;
		Connection connect = ConnectDB.getConnect();
		Statement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				String query = "select TOP 1 * from ChiTietDatPhong ORDER BY MaChiTietDatPhong DESC";
				statement = connect.createStatement();
				result = statement.executeQuery(query);
				while (result.next()) {
					String maChiTietDatPhong = result.getString(1);
					PhongEntity phongEntity = quanLyPhongDAO.timTheoMa(result.getString(2));
					LocalDate ngayNhanPhong = result.getDate(3).toLocalDate();
					LocalTime gioNhanPhong = result.getTime(4).toLocalTime();
					LocalTime gioTraPhong = result.getTime(5).toLocalTime();
					chiTietDatPhongEntity = new ChiTietDatPhongEntity(maChiTietDatPhong, phongEntity, gioNhanPhong,
							gioTraPhong, ngayNhanPhong);
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
		return chiTietDatPhongEntity;
	}

	public ChiTietDatPhongEntity timChiTietDatPhongTheoMaPhongDeNhanPhong(String maPhong) {
		ChiTietDatPhongEntity chiTietDatPhongEntity = null;
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				// tìm chiTietDatPhong có ngayNhanPhong là hôm nay. GioNhanPhong phải trước giờ
				// hiện tại 5 phút hoặc sau giờ hiện tại 10 phút => quy định nhận phòng của quán
				String query = "SELECT MaChiTietDatPhong, MaPhong, NgayDatPhong, GioNhanPhong, GioTraPhong\r\n"
						+ "FROM ChiTietDatPhong\r\n"
						+ "WHERE MaPhong LIKE ? AND NgayDatPhong = ? AND (GioNhanPhong >= ? AND GioNhanPhong <= ?)";
				statement = connect.prepareStatement(query);
				statement.setString(1, maPhong);
				statement.setString(2, DateFormatter.formatSql(LocalDate.now()));
				statement.setString(3, TimeFormatter.formatSql(LocalTime.now().minusMinutes(5)));
				statement.setString(4, TimeFormatter.formatSql(LocalTime.now().plusMinutes(20)));
				result = statement.executeQuery();
				while (result.next()) {
					String maChiTietDatPhong = result.getString(1);
					LocalDate ngayDatPhong = result.getDate(3).toLocalDate();
					LocalTime gioNhanPhong = result.getTime(4).toLocalTime();
					LocalTime gioTraPhong = result.getTime(5).toLocalTime();
					PhongEntity phongEntity = quanLyPhongDAO.timTheoMa(maPhong);
					chiTietDatPhongEntity = new ChiTietDatPhongEntity(maChiTietDatPhong, phongEntity, gioNhanPhong,
							gioTraPhong, ngayDatPhong);
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
		return chiTietDatPhongEntity;
	}

	/*********************************************************
	 * CHI TIẾT DỊCH VỤ
	 *********************************************************/

	public List<ChiTietDichVuEntity> duyetDanhSachChiTietDichVuTheoChiTietHoaDon(String maChiTietHoaDon) {
		List<ChiTietDichVuEntity> listChiTietDichVu = new ArrayList<>();
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				String query = "SELECT * FROM ChiTietDichVu WHERE MaChiTietHoaDon LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maChiTietHoaDon);
				result = statement.executeQuery();
				while (result.next()) {
					String maChiTietDichVu = result.getString(1);
					String maDichVu = result.getString(2);
					int soLuong = result.getInt(3);
					DichVuEntity dichVuEntity = quanLyDichVuDAO.timTheoMa(maDichVu);
					ChiTietDichVuEntity chiTietDichVuEntity = new ChiTietDichVuEntity(maChiTietDichVu, dichVuEntity,
							soLuong);
					listChiTietDichVu.add(chiTietDichVuEntity);
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
		return listChiTietDichVu;
	}

	public boolean xoaChiTietDichvuTheoMaHoaDon(String maChiTietHoaDon) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "DELETE FROM ChiTietDichVu WHERE MaChiTietHoaDon LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maChiTietHoaDon);
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

	public boolean xoaChiTietDichvuTheoMaChiTietDichVu(String maChiTietDichVu) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "DELETE FROM ChiTietDichVu WHERE MaChiTietDichVu LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maChiTietDichVu);
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

	public boolean themChiTietDichVu(ChiTietDichVuEntity chiTietDichVuEntity, ChiTietHoaDonEntity chiTietHoaDonEntity) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "INSERT INTO ChiTietDichVu (MaDichVu, MaChiTietHoaDon, SoLuong)\r\n"
						+ "VALUES (?, ?, ?)";
				statement = connect.prepareStatement(query);
				statement.setString(1, chiTietDichVuEntity.getDichVu().getMaDichVu());
				statement.setString(2, chiTietHoaDonEntity.getMaChiTietHoaDon());
				statement.setInt(3, chiTietDichVuEntity.getSoLuong());
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

	public boolean chinhSuaChiTietDichVuTheoMa(String maChiTietDichVu, int soLuong) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "UPDATE ChiTietDichVu\r\n" + "SET SoLuong = ?\r\n" + "WHERE MaChiTietDichVu LIKE ?";
				statement = connect.prepareStatement(query);
				statement.setInt(1, soLuong);
				statement.setString(2, maChiTietDichVu);
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
	 * HÓA ĐƠN
	 *********************************************************/

}
