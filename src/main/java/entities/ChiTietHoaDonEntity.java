package entities;

import java.util.List;
import java.util.Objects;

public class ChiTietHoaDonEntity {
	private String maChiTietHoaDon;
	private String maHoaDon;
	private ChiTietDatPhongEntity chiTietDatPhong;
	private List<ChiTietDichVuEntity> listChiTietDatDichVu;

	public ChiTietHoaDonEntity() {
		super();
	}

	public ChiTietHoaDonEntity(String maChiTietHoaDon, String maHoaDon, ChiTietDatPhongEntity chiTietDatPhong,
			List<ChiTietDichVuEntity> listChiTietDatDichVu) {
		super();
		this.maChiTietHoaDon = maChiTietHoaDon;
		this.maHoaDon = maHoaDon;
		this.chiTietDatPhong = chiTietDatPhong;
		this.listChiTietDatDichVu = listChiTietDatDichVu;
	}

	public ChiTietHoaDonEntity(String maHoaDon, ChiTietDatPhongEntity chiTietDatPhong,
			List<ChiTietDichVuEntity> listChiTietDatDichVu) {
		super();
		this.maHoaDon = maHoaDon;
		this.chiTietDatPhong = chiTietDatPhong;
		this.listChiTietDatDichVu = listChiTietDatDichVu;
	}

	public ChiTietHoaDonEntity(ChiTietDatPhongEntity chiTietDatPhong, List<ChiTietDichVuEntity> listChiTietDatDichVu) {
		super();
		this.chiTietDatPhong = chiTietDatPhong;
		this.listChiTietDatDichVu = listChiTietDatDichVu;
	}

	public ChiTietHoaDonEntity(String maChiTietHoaDon, ChiTietDatPhongEntity chiTietDatPhong) {
		super();
		this.maChiTietHoaDon = maChiTietHoaDon;
		this.chiTietDatPhong = chiTietDatPhong;
	}

	public ChiTietHoaDonEntity(String maChiTietHoaDon, String maHoaDon, ChiTietDatPhongEntity chiTietDatPhong) {
		super();
		this.maChiTietHoaDon = maChiTietHoaDon;
		this.maHoaDon = maHoaDon;
		this.chiTietDatPhong = chiTietDatPhong;
	}

	public String getMaChiTietHoaDon() {
		return maChiTietHoaDon;
	}

	public void setMaChiTietHoaDon(String maChiTietHoaDon) {
		this.maChiTietHoaDon = maChiTietHoaDon;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public ChiTietDatPhongEntity getChiTietDatPhong() {
		return chiTietDatPhong;
	}

	public void setChiTietDatPhong(ChiTietDatPhongEntity chiTietDatPhong) {
		this.chiTietDatPhong = chiTietDatPhong;
	}

	public List<ChiTietDichVuEntity> getListChiTietDatDichVu() {
		return listChiTietDatDichVu;
	}

	public void setListChiTietDatDichVu(List<ChiTietDichVuEntity> listChiTietDatDichVu) {
		this.listChiTietDatDichVu = listChiTietDatDichVu;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maChiTietHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		ChiTietHoaDonEntity other = (ChiTietHoaDonEntity) obj;
		return Objects.equals(maChiTietHoaDon, other.maChiTietHoaDon);
	}

	@Override
	public String toString() {
		return "ChiTietHoaDonEntity [maChiTietHoaDon=" + maChiTietHoaDon + ", maHoaDon=" + maHoaDon
				+ ", chiTietDatPhong=" + chiTietDatPhong + ", listChiTietDatDichVu=" + listChiTietDatDichVu + "]";
	}

}
