package entities;

import java.util.Objects;

public class ChiTietPhieuDatPhongEntity {
	private String maChiTietPhieuDatPhong;
	private String maPhieuDatPhong;
	private String maChiTietDatPhong;

	public ChiTietPhieuDatPhongEntity() {
		super();
	}

	public ChiTietPhieuDatPhongEntity(String maChiTietPhieuDatPhong, String maPhieuDatPhong, String maChiTietDatPhong) {
		super();
		this.maChiTietPhieuDatPhong = maChiTietPhieuDatPhong;
		this.maPhieuDatPhong = maPhieuDatPhong;
		this.maChiTietDatPhong = maChiTietDatPhong;
	}

	public ChiTietPhieuDatPhongEntity(String maPhieuDatPhong, String maChiTietDatPhong) {
		super();
		this.maPhieuDatPhong = maPhieuDatPhong;
		this.maChiTietDatPhong = maChiTietDatPhong;
	}

	public String getMaChiTietPhieuDatPhong() {
		return maChiTietPhieuDatPhong;
	}

	public void setMaChiTietPhieuDatPhong(String maChiTietPhieuDatPhong) {
		this.maChiTietPhieuDatPhong = maChiTietPhieuDatPhong;
	}

	public String getMaPhieuDatPhong() {
		return maPhieuDatPhong;
	}

	public void setMaPhieuDatPhong(String maPhieuDatPhong) {
		this.maPhieuDatPhong = maPhieuDatPhong;
	}

	public String getMaChiTietDatPhong() {
		return maChiTietDatPhong;
	}

	public void setMaChiTietDatPhong(String maChiTietDatPhong) {
		this.maChiTietDatPhong = maChiTietDatPhong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maChiTietPhieuDatPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		ChiTietPhieuDatPhongEntity other = (ChiTietPhieuDatPhongEntity) obj;
		return Objects.equals(maChiTietPhieuDatPhong, other.maChiTietPhieuDatPhong);
	}

	@Override
	public String toString() {
		return "ChiTietPhieuDatPhongEntity [maChiTietPhieuDatPhong=" + maChiTietPhieuDatPhong + ", maPhieuDatPhong="
				+ maPhieuDatPhong + ", maChiTietDatPhong=" + maChiTietDatPhong + "]";
	}

}
