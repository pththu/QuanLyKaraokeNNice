package gui.hoaDon;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.QuanLyHoaDonDAO;
import dao.QuanLyPhieuDatPhongDAO;
import entities.ChiTietDatPhongEntity;
import entities.ChiTietDichVuEntity;
import entities.ChiTietHoaDonEntity;
import entities.ChiTietPhieuDatPhongEntity;
import entities.PhieuDatPhongEntity;
import util.MoneyFormatter;
import util.TimeFormatter;

public class GD_XemChiTietHoaDonLapHoaDon extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JTable tblPhong;
	private JTable tblDichVu;

	private DefaultTableModel tblmodelPhong;
	private DefaultTableModel tblmodelDichVu;

	private JButton btnThoat;
	private JButton btnLamMoi;

	private PhieuDatPhongEntity phieuDatPhongEntity;
	private JTextField txtSoPhong;

	private List<ChiTietPhieuDatPhongEntity> listChiTietPhieuDatPhong;
	private List<ChiTietDichVuEntity> listChiTietDichVu;

	private QuanLyHoaDonDAO quanLyHoaDonDAO = new QuanLyHoaDonDAO();
	private QuanLyPhieuDatPhongDAO quanLyPhieuDatPhongDAO = new QuanLyPhieuDatPhongDAO();

	/**
	 * Create the frame.
	 */
	public GD_XemChiTietHoaDonLapHoaDon(PhieuDatPhongEntity phieuDatPhongEntity) {
		this.phieuDatPhongEntity = phieuDatPhongEntity;
		setBackground(new Color(230, 230, 250));
		setTitle("Xem chi tiết hóa đơn");
		pnlContent = new JPanel();
		pnlContent.setBackground(new Color(230, 230, 250));
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);
		setSize(903, 476);
		setLocationRelativeTo(null);

		JPanel pnlChiTietPhong = new JPanel();
		pnlChiTietPhong.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh sách phòng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlChiTietPhong.setBackground(new Color(230, 230, 250));
		pnlChiTietPhong.setBounds(10, 11, 873, 180);
		pnlContent.add(pnlChiTietPhong);
		pnlChiTietPhong.setLayout(null);

		String[] col_Phong = { "STT", "Số phòng", "Loại phòng", "Sức chứa", "Giờ nhận phòng", "Giờ trả phòng" };
		tblmodelPhong = new DefaultTableModel(col_Phong, 0);
		tblPhong = new JTable(tblmodelPhong);

		JScrollPane scrPhong = new JScrollPane(tblPhong);
		scrPhong.setBounds(20, 20, 827, 149);
		pnlChiTietPhong.add(scrPhong);

		JPanel pnlChiTietDichVu = new JPanel();
		pnlChiTietDichVu.setBorder(
				new TitledBorder(null, "Danh sách dịch vụ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlChiTietDichVu.setBackground(new Color(230, 230, 250));
		pnlChiTietDichVu.setBounds(10, 202, 873, 224);
		pnlContent.add(pnlChiTietDichVu);
		pnlChiTietDichVu.setLayout(null);

		String[] col_DichVu = { "STT", "Tên dịch vụ", "Loại dịch vụ", "Giá tiền", "Số lượng", "Thành tiền" };
		tblmodelDichVu = new DefaultTableModel(col_DichVu, 0);
		tblDichVu = new JTable(tblmodelDichVu);

		JScrollPane scrDichVu = new JScrollPane(tblDichVu);
		scrDichVu.setBounds(20, 56, 827, 157);
		pnlChiTietDichVu.add(scrDichVu);

		JLabel lblSoPhong = new JLabel("Số phòng:");
		lblSoPhong.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblSoPhong.setBounds(20, 22, 70, 20);
		pnlChiTietDichVu.add(lblSoPhong);

		txtSoPhong = new JTextField();
		txtSoPhong.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtSoPhong.setForeground(Color.BLACK);
		txtSoPhong.setEditable(false);
		txtSoPhong.setBounds(89, 20, 86, 25);
		pnlChiTietDichVu.add(txtSoPhong);
		txtSoPhong.setColumns(10);

		btnThoat = new JButton("Thoát");
		btnThoat.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnThoat.setBackground(new Color(144, 238, 144));
		btnThoat.setFocusable(false);
		btnThoat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnThoat.setBounds(750, 456, 120, 35);
		pnlContent.add(btnThoat);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLamMoi.setBackground(new Color(144, 238, 144));
		btnLamMoi.setFocusable(false);
		btnLamMoi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnLamMoi.setBounds(610, 456, 120, 35);
		pnlContent.add(btnLamMoi);

		tblPhong.addMouseListener(this);
		loadPhong();
	}

	private void loadPhong() {
		listChiTietPhieuDatPhong = new ArrayList<>();
		listChiTietPhieuDatPhong = quanLyPhieuDatPhongDAO
				.duyetDanhSachChiTietPhieuDatPhongTheoPhieuDatPhong(phieuDatPhongEntity.getMaPhieuDatPhong());
		tblPhong.removeAll();
		tblPhong.setRowSelectionAllowed(false);
		tblmodelPhong.setRowCount(0);

		int stt = 1;
		for (ChiTietPhieuDatPhongEntity chiTietPhieuDatPhongEntity : listChiTietPhieuDatPhong) {
			ChiTietDatPhongEntity chiTietDatPhongEntity = quanLyHoaDonDAO
					.timChiTietDatPhongTheoMa(chiTietPhieuDatPhongEntity.getMaChiTietDatPhong());
			tblmodelPhong.addRow(new Object[] { stt++, chiTietDatPhongEntity.getPhong().getSoPhong(),
					chiTietDatPhongEntity.getPhong().getLoaiPhong().getTenLoaiPhong(),
					chiTietDatPhongEntity.getPhong().getSucChua(),
					TimeFormatter.format(chiTietDatPhongEntity.getGioNhanPhong()),
					TimeFormatter.format(chiTietDatPhongEntity.getGioTraPhong()) });
		}
	}

	public void chonPhong() {
		int row = tblPhong.getSelectedRow();
		listChiTietPhieuDatPhong = quanLyPhieuDatPhongDAO
				.duyetDanhSachChiTietPhieuDatPhongTheoPhieuDatPhong(phieuDatPhongEntity.getMaPhieuDatPhong());
		if (row >= 0) {
			txtSoPhong.setText(tblPhong.getValueAt(row, 1).toString());
			ChiTietDatPhongEntity chiTietDatPhongEntity = quanLyHoaDonDAO
					.timChiTietDatPhongTheoMa(listChiTietPhieuDatPhong.get(row).getMaChiTietDatPhong());
			ChiTietHoaDonEntity chiTietHoaDonEntity = quanLyHoaDonDAO
					.timChiTietHoaDonTheoChiTietDatPhong(chiTietDatPhongEntity);
			listChiTietDichVu = quanLyHoaDonDAO
					.duyetDanhSachChiTietDichVuTheoChiTietHoaDon(chiTietHoaDonEntity.getMaChiTietHoaDon());
			tblDichVu.removeAll();
			tblDichVu.setRowSelectionAllowed(false);
			tblmodelDichVu.setRowCount(0);
			int stt = 1;
			for (ChiTietDichVuEntity chiTietDichVuEntity : listChiTietDichVu) {
				tblmodelDichVu.addRow(new Object[] { stt++, chiTietDichVuEntity.getDichVu().getTenDichVu(),
						chiTietDichVuEntity.getDichVu().getLoaiDichVu().getTenLoaiDichVu(),
						MoneyFormatter.format(chiTietDichVuEntity.getDichVu().getGia()),
						chiTietDichVuEntity.getSoLuong(), MoneyFormatter.format(tinhThanhTien(chiTietDichVuEntity)) });
			}
		}
	}

	public void chonLamMoi() {
		txtSoPhong.setText("");
		tblDichVu.removeAll();
		tblDichVu.setRowSelectionAllowed(false);
		tblmodelDichVu.setRowCount(0);
	}

	public void chonThoat() {
		this.dispose();
	}

	private double tinhThanhTien(ChiTietDichVuEntity chiTietDichVuEntity) {
		return chiTietDichVuEntity.getDichVu().getGia() * chiTietDichVuEntity.getSoLuong();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLamMoi)) {

		} else if (o.equals(btnThoat)) {

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		chonPhong();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
