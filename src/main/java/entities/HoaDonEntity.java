package entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class HoaDonEntity {
	private String maHoaDon;
	private String maNhanVien;
	private String maKhachHang;
	private LocalDate ngayLap;
	private LocalTime gioLap;

	public HoaDonEntity() {
		super();
	}

	public HoaDonEntity(String maHoaDon, String maNhanVien, String maKhachHang, LocalDate ngayLap, LocalTime gioLap) {
		super();
		this.maHoaDon = maHoaDon;
		this.maNhanVien = maNhanVien;
		this.maKhachHang = maKhachHang;
		this.ngayLap = ngayLap;
		this.gioLap = gioLap;
	}

	public HoaDonEntity(String maNhanVien, String maKhachHang, LocalDate ngayLap, LocalTime gioLap) {
		super();
		this.maNhanVien = maNhanVien;
		this.maKhachHang = maKhachHang;
		this.ngayLap = ngayLap;
		this.gioLap = gioLap;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public LocalDate getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}

	public LocalTime getGioLap() {
		return gioLap;
	}

	public void setGioLap(LocalTime gioLap) {
		this.gioLap = gioLap;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		HoaDonEntity other = (HoaDonEntity) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}

	@Override
	public String toString() {
		return "HoaDonEntity [maHoaDon=" + maHoaDon + ", maNhanVien=" + maNhanVien + ", maKhachHang=" + maKhachHang
				+ ", ngayLap=" + ngayLap + ", gioLap=" + gioLap + "]";
	}

}
