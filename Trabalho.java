import java.util.ArrayList;

class Streaming
{
    private static Streaming instance;
    public static Streaming getInstance() {
        if (instance == null) {
	    instance = new Streaming();
        }
        return instance;
    }

    public String senha; 	
    public String login;	 
    public String tipoUsuario; 		//Plano
    public Diretorio diretorio; //Diretorio de musicas

    public void infoUsuario(String login, String senha, String tipoUsuario)
    {
	this.login = login;
	this.senha = senha;
	this.tipoUsuario = tipoUsuario;
    }
    public void addPlaylist (Playlist lista)
    {
	this.diretorio.playlists.add(lista);
    }

    public void accept (Visitor visita){
	for (int i=1; i<=5; i++)
	    this.diretorio.get(i).accept1(visita);
    }
}

class Diretorio
{
    
    ArrayList<Playlist> playlists;
 
}

class Playlist extends Diretorio
{
    ArrayList<Album> albuns;
    String nome, estilo, estiloSec;

    public void criaPlaylist (String name, String style, String style2)
    {
	this.nome = nome;
	this.estilo = style;
	this.estiloSec = style2;
    }

    public void addAlbum (Album musicAlbum)
    {
	this.albuns.add(musicAlbum);
    }
    
    public void addLista (Playlist lista)
    {
	this.playlists.add(lista);
    }

    public void accept1 (Visitor visita)
    {
	visita.visita(this);
	for (int i=1; i<=4; i++)
	    this.albuns.get(i).accept2(visita);
    }
}

class Album extends Diretorio
{
    ArrayList<Musica> musicas;
    String nome, banda, estilo;

    public void criaAlbum (String name, String style, String band)
    {
	this.nome = name;
	this.banda = band;
	this.estilo = style;
    }

    public void addMusica (Musica music)
    {
	this.musicas.add(music);
    }

    public void accept2 (Visitor visita)
    {
	visita.visita(this);
	for(int i=1; i<=5; i++)
	    this.musicas.get(i).accept3(visita);
    }
}

class Musica
{
    int duracao; // em segundos
    String nome, estilo;

    public void criaMusica (String name, String style, int dur)
    {
	this.duracao = dur;
	this.nome = name;
	this.estilo = style;
    }

    public void accept3 (Visitor visita)
    {
	visita.visitaMusica(this);
    }
}


interface Visitor
{
    abstract void visita(Diretorio elemento);
    abstract void visitaMusica(Musica musica);
    abstract void visita(Diretorio elemento, String estilo);
    abstract void visitaMusica(Musica musica, String estilo);
}

class VisitanteTodos implements Visitor
{
    public void visita (Diretorio elemento){
	if (elemento.getClass() == Playlist.class || elemento.getClass() == Album.class)
	    System.out.println("Nome: ", +elemento.nome, " & Estilo: ", +elemento.estilo);
    }
    
    public void visitaMusica (Musica musica){
	System.out.println("Nome: ", +musica.nome, " & Estilo: ", musica.estilo);
    }
}

class VisitanteEstilo implements Visitor
{
    public void visita (Diretorio elemento, String estilo){}

    public void visitaMusica (Musica musica, String estilo){
	if (musica.estilo == estilo)
	    System.out.println("Nome: ", +musica.nome, " & Estilo: ", musica.estilo);
    }
}   
 
class VisitanteTempo implements Visitor
{
   public void visita (Diretorio elemento, int String){
	if (elemento.getClass() == Playlist.class){
	    if (elemento.estilo == estilo)
		System.out.println("Nome: ", +estilo.nome, " & Estilo: ", estilo.estilo);
	    else if (elemento.estiloSec == estilo)
		System.out.println("Nome: ", +estilo.nome, " & Estilo: ", estilo.estiloSec);
	}
	else
	    if (elemento.estilo == estilo)
		System.out.println("Nome: ", +estilo.nome, " & Estilo: ", estilo.estilo);
    }

    public void visitaMusica (Musica musica, int String){
	 if (musica.estilo == estilo && musica.duracao > 180)
		System.out.println("Nome: ", +musica.nome, " & Estilo: ", musica.estilo);
    }
}

public class Trabalho{
    public static void main(String[] args){
        //Instanciação e definição de parametros de um serviço de streaming
        
	Streaming servico = Streaming.getInstance();
	servico.infoUsuario("dummy","password","free");
       
        //Instanciando playlists, albuns e musicas
        for(int i = 1; i <= 5; i++){
            Playlist p = new Playlist();
	    p.criaPlaylist("Playlist"+i, "Estilo"+i, "EstiloSecundario"+i);
            for(int j = 1; j <= 4; j++){
                Album a = new Album();
		a.criaAlbum("Album"+(i*4+j), "Estilo"+i, "NomeBanda"+j);
                for(int k = 1; k <= 5; k++){
                    Musica m = new Musica();
		    m.criaMusica("Musica"+(i*4+j)+"-"+k, "Estilo"+i, 120+(k*j*i));
                    a.addMusica(m);
                }
                p.addAlbum(a);
            }
            servico.addPlaylist(p);
        }

        //P1 recebe P3
        servico.diretorio.get(0).addLista(servico.diretorio.get(2));
        //P2 recebe P5
        servico.diretorio.get(1).addLista(servico.diretorio.get(4));

        //Chamando os visitantes para realizar as impressões
        System.out.println("Imprimindo todos os elementos:");
        servico.accept(new VisitanteTodos());
        System.out.println("Imprimindo as musicas do estilo 'Estilo"+1+"'");
        servico.accept(new VisitanteEstilo("Estilo"+1));
        System.out.println("Imprimindo as musicas do estilo 'Estilo"+2+"' e duração maior que 3 minutos");
        servico.accept(new VisitanteTempo("Estilo"+2));
    }
}
