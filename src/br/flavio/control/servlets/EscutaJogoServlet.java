package br.flavio.control.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.flavio.control.jogo.JogoControl;
import br.flavio.model.Jogo;

/**
 * Servlet implementation class EscutaJogoServlet
 */
@WebServlet("/EscutaJogoServlet")
public class EscutaJogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EscutaJogoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Jogo jvo = (Jogo) request.getSession().getAttribute("jogoVo");
		 if (jvo == null) { //Jogo Novo!
		 jvo = new Jogo();
		 request.getSession().setAttribute("jogoVo", jvo);
		 }
		 jvo = (Jogo) request.getSession().getAttribute("jogoVo");
		 if (jvo.getFimJogo() == 1) {
		 jvo = new Jogo();
		 }


		 String jogada = request.getParameter("txtjogada");

		 if (jogada == null) {
		 request.getSession().setAttribute("jogoVo", jvo);
		 RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		 view.forward(request, response);
		 }
		 int jogadaint = Integer.parseInt(jogada);
		 JogoControl jc = new JogoControl();
		 
		//Valida
		 if (jc.verificaJogada(jogadaint,jvo) == 1)
		 jvo = jc.realizaJogada(jogadaint,jvo);
		 else
		 jvo.setMsg("JOGADA INVALIDA!");
		 if (jc.verificaFimDeJogo(jvo) == 1) {
		 jvo.setFimJogo(1);

		 if (jc.verificaVencedor(jvo) == 1)
		 jvo.setMsg("Fim de Jogo! Vencedor Jogador 1");
		 else
		 if (jc.verificaVencedor(jvo) == 2)
		 jvo.setMsg("Fim de Jogo! Vencedor Jogador 2");
		 else
		 jvo.setMsg("Fim de Jogo! DEU VELHA!");
		 }
		 request.getSession().setAttribute("jogoVo", jvo);
		 RequestDispatcher view = request.getRequestDispatcher("JogoView.jsp");
		 view.forward(request, response);
		 } 
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 // TODO Auto-generated method stub
		 //Codigo da minha aplicacao!

	}
}
