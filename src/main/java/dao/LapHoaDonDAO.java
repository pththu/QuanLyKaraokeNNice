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
import entities.ChiTietHoaDonEntity;
import entities.HoaDonEntity;
import entities.KhachHangEntity;
import entities.PhieuDatPhongEntity;
import util.ConnectDB;

public class LapHoaDonDAO {

	private QuanLyHoaDonDAO quanLyHoaDonDAO = new QuanLyHoaDonDAO();

	public LapHoaDonDAO() {

	}

	public int demSoLuongPhong(String maPhieuDatPhong) {
		int soLuong = 0;
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "select count(*) as 'So luong phong' \r\n" + "from ChiTietPhieuDatPhong\r\n"
						+ "where MaPhieuDatPhong = ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maPhieuDatPhong);
				result = statement.executeQuery();
				while (result.next()) {
					soLuong = result.getInt(1);
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
		return soLuong;
	}

	public double tinhTienDichVu(String maPhieuDatPhong) {
		double tienDichVu = 0;
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "select SUM(ctdv.SoLuong * Gia) as N'Tổng tiền dịch vụ'\r\n"
						+ "from ChiTietDichVu ctdv join DichVu dv on dv.MaDichVu = ctdv.MaDichVu\r\n"
						+ "join ChiTietHoaDon cthd on ctdv.MaChiTietHoaDon = cthd.MaChiTietHoaDon\r\n"
						+ "join ChiTietDatPhong ctdp on ctdp.MaChiTietDatPhong = cthd.MaChiTietDatPhong\r\n"
						+ "join ChiTietPhieuDatPhong ctpdp on ctpdp.MaChiTietDatPhong = ctdp.MaChiTietDatPhong\r\n"
						+ "join PhieuDatPhong pdp on pdp.MaPhieuDatPhong = ctpdp.MaPhieuDatPhong\r\n"
						+ "where pdp.MaPhieuDatPhong = ? and cthd.MaHoaDon is null";
				statement = connect.prepareStatement(query);
				statement.setString(1, maPhieuDatPhong);
				result = statement.executeQuery();
				while (result.next()) {
					tienDichVu = result.getDouble(1);
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
		return tienDichVu;
	}

	public boolean themHoaDon(HoaDonEntity hoaDonEntity) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "insert HoaDon (MaNhanVien, MaKhachHang, NgayLap, GioLap)\r\n" + "values ( ?, ?, ?, ?)";
				statement = connect.prepareStatement(query);
				statement.setString(1, hoaDonEntity.getMaNhanVien());
				statement.setString(2, hoaDonEntity.getMaKhachHang());
				statement.setDate(3, Date.valueOf(hoaDonEntity.getNgayLap()));
				statement.setTime(4, Time.valueOf(hoaDonEntity.getGioLap()));
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

	public List<ChiTietHoaDonEntity> duyetDanhSachChiTietHoaDon(String maPhieuDatPhong) {
		List<ChiTietHoaDonEntity> listChiTietHoaDon = new ArrayList<ChiTietHoaDonEntity>();
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "select *from ChiTietHoaDon cthd\r\n"
						+ "where exists (select *from ChiTietDatPhong ctdp \r\n"
						+ "join ChiTietPhieuDatPhong ctpdp on ctdp.MaChiTietDatPhong = ctpdp.MaChiTietDatPhong\r\n"
						+ "join PhieuDatPhong pdp on pdp.MaPhieuDatPhong = ctpdp.MaPhieuDatPhong\r\n"
						+ "where ctdp.MaChiTietDatPhong = cthd.MaChiTietDatPhong and pdp.MaPhieuDatPhong = ?)";
				statement = connect.prepareStatement(query);
				statement.setString(1, maPhieuDatPhong);
				result = statement.executeQuery();
				while (result.next()) {
					String maChiTietHoaDon = result.getString(1);
					String maHoaDon = result.getString(2);
					String maChiTietDatPhong = result.getString(3);

					ChiTietDatPhongEntity chiTietDatPhongEntity = quanLyHoaDonDAO
							.timChiTietDatPhongTheoMa(maChiTietDatPhong);
					ChiTietHoaDonEntity chiTietHoaDonEntity = new ChiTietHoaDonEntity(maChiTietHoaDon, maHoaDon,
							chiTietDatPhongEntity);
					listChiTietHoaDon.add(chiTietHoaDonEntity);

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
		return listChiTietHoaDon;
	}

	public boolean capNhatChiTietHoaDon(ChiTietHoaDonEntity chiTietHoaDonEntity, String maHoaDon) {
		Connection connect = ConnectDB.getConnect();
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "Update ChiTietHoaDon\r\n" + "set MaHoaDon = ?\r\n" + "where MaChiTietHoaDon = ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, maHoaDon);
				statement.setString(2, chiTietHoaDonEntity.getMaChiTietHoaDon());
				return statement.executeUpdate() > 0;
			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				ConnectDB.closeConnect(connect);
				ConnectDB.closePreStatement(statement);
			}
		}
		return false;
	}

	public HoaDonEntity timHoaDonVuaTao() {
		HoaDonEntity hoaDonEntity = null;
		Connection connect = ConnectDB.getConnect();
		Statement statement = null;
		ResultSet result = null;

		if (connect != null) {
			try {
				String query = "select top 1 *from HoaDon order by MaHoaDon desc";
				statement = connect.createStatement();
				result = statement.executeQuery(query);
				while (result.next()) {
					String maHoaDon = result.getString(1);
					String maNhanVien = result.getString(2);
					String maKhachHang = result.getString(3);
					LocalDate ngayLap = result.getDate(4).toLocalDate();
					LocalTime gioLap = result.getTime(5).toLocalTime();
					hoaDonEntity = new HoaDonEntity(maHoaDon, maNhanVien, maKhachHang, ngayLap, gioLap);
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
		return hoaDonEntity;
	}

	public List<PhieuDatPhongEntity> timHoaDonTheoKhachHang(KhachHangEntity khachHangEntity) {
		List<PhieuDatPhongEntity> listPhieuDatPhong = new ArrayList<PhieuDatPhongEntity>();
		Connection connect = ConnectDB.getConnect();
		ResultSet result = null;
		PreparedStatement statement = null;
		if (connect != null) {
			try {
				String query = "select *from PhieuDatPhong pdp\r\n"
						+ "where exists (select *from ChiTietPhieuDatPhong ctpdp \r\n"
						+ "join ChiTietDatPhong ctdp on ctdp.MaChiTietDatPhong = ctpdp.MaChiTietDatPhong\r\n"
						+ "join ChiTietHoaDon cthd on cthd.MaChiTietDatPhong = ctdp.MaChiTietDatPhong\r\n"
						+ "join ChiTietDichVu ctdv on cthd.MaChiTietHoaDon = ctdv.MaChiTietHoaDon\r\n"
						+ "join DichVu dv on dv.MaDichVu = ctdv.MaDichVu\r\n"
						+ "where pdp.MaPhieuDatPhong = ctpdp.MaPhieuDatPhong and cthd.MaHoaDon is null) \r\n"
						+ "and MaKhachHang = ?";
				statement = connect.prepareStatement(query);
				statement.setString(1, khachHangEntity.getMaKhachHang());
				result = statement.executeQuery();
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
				ConnectDB.closeResultSet(result);
				ConnectDB.closeStatement(statement);
			}
		}
		return listPhieuDatPhong;
	}

}
