import java.util.*;

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
	this.diretorio = new Diretorio();
    }
    public void addPlaylist (Playlist lista)
    {
	if (this.diretorio.playlists == null)
	    this.diretorio.playlists = new ArrayList<Playlist>();
	this.diretorio.playlists.add(lista);
    }

    public void accept (Visitor visita){
	for (int i=0; i<5; i++)
	    this.diretorio.playlists.get(i).accept1(visita);
    }
}

class Diretorio
{
    
    ArrayList<Playlist> playlists;
 
}

class Playlist extends Diretorio
{
    ArrayList<Album> albuns;
    ArrayList<Playlist> lista;
    String nome, estilo, estiloSec;

    public void criaPlaylist (String name, String style, String style2)
    {
	this.nome = name;
	this.estilo = style;
	this.estiloSec = style2;
    }

    public void addAlbum (Album musicAlbum)
    {
	if (albuns == null)
	    albuns = new ArrayList<Album>();
	this.albuns.add(musicAlbum);
    }
    
    public void addLista (Playlist lista)
    {
	if (this.lista == null)
	    this.lista = new ArrayList<Playlist>();
	this.lista.add(lista);
    }

    public void accept1 (Visitor visita)
    {
	visita.visitaLista(this);
	for (int i=0; i<4; i++)
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
	if (musicas == null)
	    musicas = new ArrayList<Musica>();
	this.musicas.add(music);
    }

    public void accept2 (Visitor visita)
    {
	visita.visitaAlbum(this);
	for(int i=0; i<5; i++)
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
    abstract void visitaLista(Playlist elemento);
    abstract void visitaAlbum(Album elemento);
    abstract void visitaMusica(Musica musica);
    abstract void visitaLista(Playlist elemento, String estilo);
    abstract void visitaAlbum(Album elemento, String estilo);
    abstract void visitaMusica(Musica musica, String estilo);
}

class VisitanteTodos implements Visitor
{
    public void visitaLista (Playlist elemento){
	System.out.println("Nome: "+elemento.nome+" & Estilo: "+elemento.estilo);
    }

    public void visitaAlbum (Album elemento){
	System.out.println("Nome: "+elemento.nome+" & Estilo: "+elemento.estilo);
    }
    
    public void visitaMusica (Musica musica){
	System.out.println("Nome: "+musica.nome+" & Estilo: "+musica.estilo);
    }

    public void visitaLista (Playlist elemento, String estilo){}
    public void visitaAlbum (Album elemento, String estilo){}
    public void visitaMusica (Musica musica, String estilo){}

}

class VisitanteEstilo implements Visitor
{
    String estilo;
    
    VisitanteEstilo (String n){
	this.estilo = n;
    }
    public void visitaLista (Playlist elemento){}
    public void visitaAlbum (Album elemento){}
    public void visitaMusica (Musica elemento){}

    public void visitaLista (Playlist elemento, String estilo){}
    public void visitaAlbum (Album elemento, String estilo){}
    public void visitaMusica (Musica musica, String estilo){
	if (musica.estilo.equals(this.estilo))
	    System.out.println("Nome: "+musica.nome+" & Estilo: "+musica.estilo);
    }
}   
 
class VisitanteTempo implements Visitor
{
    String estilo;
    
    VisitanteTempo (String n){
	this.estilo = n;
    }

    public void visitaLista (Playlist elemento){}
    public void visitaAlbum (Album elemento){}
    public void visitaMusica (Musica musica){}
   
    public void visitaLista (Playlist elemento, String estilo){
	    if (elemento.estilo.equals(this.estilo))
		System.out.println("Nome: "+elemento.nome+" & Estilo: "+elemento.estilo);
	    else if (elemento.estiloSec.equals(this.estilo))
		System.out.println("Nome: "+elemento.nome+" & Estilo: "+elemento.estiloSec);
	}
    public void visitaAlbum (Album elemento, String estilo){}

    public void visitaMusica (Musica musica, String estilo){
	if (musica.estilo.equals(this.estilo) && musica.duracao > 180)
		System.out.println("Nome: "+musica.nome+" & Estilo: "+musica.estilo);
    }
}

public class Trabalho{
    public static void main(String[] args){
        
	// Cria o serviço de streaming e coloca seus atributos básicos
	Streaming servico = Streaming.getInstance();
	servico.infoUsuario("dummy","password","free");
       
        // Instancia os diversos componentes do serviço de string
        for(int i = 0; i < 5; i++){
            Playlist p = new Playlist();
	    p.criaPlaylist("Playlist"+i, "Estilo"+i, "EstiloSecundario"+i);
            for(int j = 0; j < 4; j++){
                Album a = new Album();
		a.criaAlbum("Album"+(i*4+j), "Estilo"+i, "NomeBanda"+j);
                for(int k = 0; k < 5; k++){
                    Musica m = new Musica();
		    m.criaMusica("Musica"+(i*4+j)+"-"+k, "Estilo"+i, 120+(k*j*i));
                    a.addMusica(m);
                }
                p.addAlbum(a);
            }
            servico.addPlaylist(p);
        }

        //Playlist 1 recebe Playlist 3
        servico.diretorio.playlists.get(0).addLista(servico.diretorio.playlists.get(2));
        //Playlist 1 recebe Playlist 5
        servico.diretorio.playlists.get(1).addLista(servico.diretorio.playlists.get(4));

        //Realiza as diversas impressões

        System.out.println("Usuário deseja saber todos os seus elementos:");
        servico.accept(new VisitanteTodos());
        System.out.println("Usuário deseja saber as musicas do estilo 'Estilo"+1+"'");
        servico.accept(new VisitanteEstilo("Estilo"+1));
        System.out.println("Usuário deseja saber as musicas do estilo 'Estilo"+2+"' com duração maior que 3 minutos");
        servico.accept(new VisitanteTempo("Estilo"+2));
    }
}
