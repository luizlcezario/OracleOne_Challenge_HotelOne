package src.views;

import src.controllers.BuscarController;
import src.models.Hospedes;
import src.models.PaymentTypes;
import src.models.Reservas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private BuscarController controller = BuscarController.getInstance();
	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Buscar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
				
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), tbReservas, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Id da Reserva");
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		tbReservas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		preencherTabelaReservas();
		
		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), tbHospedes, null);

		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		tbHospedes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		preencherTabelaHospedes();

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selected = panel.getSelectedIndex();
				if (selected == 0) {
					controller.filter(txtBuscar.getText().split(":"), "reservas");
				} else if (selected == 1) {
					controller.filter(txtBuscar.getText().split(":"), "hospedes");
				} else {
					JOptionPane.showMessageDialog(null, "Erro: fileira não selecionada, ou vazia, por favor selecione um item valido para editar!");
				}
				limparTabela();
				preencherTabelaReservas();
				preencherTabelaHospedes();
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaHospedes = tbHospedes.getSelectedRow();
				int filaReservas = tbReservas.getSelectedRow();
				if (filaReservas >= 0) {
					atualizarReservas();
				} else if (filaHospedes >= 0) {
					atualizarHospede();
				} else {
					JOptionPane.showMessageDialog(null, "Erro: fileira não selecionada, ou vazia, por favor selecione um item valido para editar!");
				}
				limparTabela();
				preencherTabelaReservas();
			}
		});
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);

		btnEditar.add(lblEditar);
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaHospedes = tbHospedes.getSelectedRow();
				int filaReservas = tbReservas.getSelectedRow();

				if (filaReservas >= 0) {
					int confirmar = JOptionPane.showConfirmDialog(null, "¿Deseja excluir os dados?");
					if(confirmar == JOptionPane.YES_OPTION){
						String valor = tbReservas.getValueAt(filaReservas, 0).toString();
						controller.deletarResserva(Integer.valueOf(valor));
						JOptionPane.showMessageDialog(contentPane, "Registro Excluído");
					}

				}
				if (filaHospedes >= 0) {
					int confirmarh = JOptionPane.showConfirmDialog(null, "¿Deseja excluir os dados?");
					if(confirmarh == JOptionPane.YES_OPTION){
						String valor = tbHospedes.getValueAt(filaHospedes, 0).toString();
						controller.deletarHospedes(Integer.valueOf(valor));
						JOptionPane.showMessageDialog(contentPane, "Registro Excluído");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Erro: fileira não selecionada, por favor realize uma busca e selecione uma fileira para excluir");
				}
				limparTabela();
				preencherTabelaReservas();
				preencherTabelaHospedes();
			}
		});
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		contentPane.add(btnDeletar);
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);

		btnDeletar.add(lblExcluir);
		setResizable(false);
	}


	
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	private void preencherTabelaReservas() {

		// Povoar tabela
		java.util.List<Reservas> reservaLista = controller.getAllReservas();
		try {
			for (Reservas reserva : reservaLista) {
				Optional<PaymentTypes> pt = PaymentTypes.fromInt(reserva.getFormaPagamento());
				modelo.addRow(new Object[]{reserva.getId(), reserva.getNumeroReserva(), reserva.getDataEntrada(), reserva.getDataSaida(), reserva.getValor(), pt.get().getValue()});
				}
			} catch (Exception e) {
			throw e;
		}
	}

	private void preencherTabelaHospedes() {
		//Preencher Tabela
		List<Hospedes> hospedesLista = controller.getAllHospedes();
		try {
			for (Hospedes hospede : hospedesLista) {
				modeloHospedes.addRow(new Object[] { hospede.getId(), hospede.getNome(), hospede.getSobreNome(), hospede.getDataNascimento(), hospede.getNascionalidade(), hospede.getTelefone(), hospede.getReservas().stream().map((e) -> e.getNumeroReserva().toString()).reduce("", (subtotal, ele) -> subtotal + ele + ", ")});
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void limparTabela() {
		((DefaultTableModel) tbHospedes.getModel()).setRowCount(0);
		((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
	}
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
	private void atualizarReservas() {

		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
					Date dataEntrada = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
					Date dataSaida = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
					String valor = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
					String formaPagamento = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 5);
					this.controller.atualizarReservas(dataEntrada,dataSaida, valor, formaPagamento, id);
					JOptionPane.showMessageDialog(this, String.format("Registro modificado com êxito"));
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, escolha um registro"));
	}

	private void atualizarHospede() {
		Optional.ofNullable(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn()))
				.ifPresentOrElse(filaHuesped -> {
					Integer id = Integer.valueOf(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 0).toString());
					String nome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 1);
					String sobrenome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 2);
					Date dataNascimento = Date.valueOf(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 3).toString());
					String nacionalidade = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 4);
					String telefone = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 5);
					this.controller.atualizarHospede(nome,sobrenome,dataNascimento, nacionalidade, telefone, id);
					JOptionPane.showMessageDialog(this, String.format("Registro modificado com êxito"));
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, escolha um registro"));

	}

}
