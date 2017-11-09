import java.util.ArrayList;

class Streaming
{
    private Streaming();
    private static Streaming instance=NULL;
    
    public Streaming instanciaStreaming(string, string); //instancia uma
    public String senha; 	
    public String login;	 
    public int tipoUsuario; 		//Plano
    public Diretorio diretorio; //Diretorio de musicas

}

class Elemento
{

}

class Diretorio extends Elemento
{
    ArrayList<Playlist> playlists;

    public void addPlaylist (Playlist lista)
    {
	this.playlists.add(lista);
    }

}

class Playlist extends Diretorio
{
    ArrayList<Album> albuns;
    String nome, estilo, estiloSec;

    public void criaPlaylsit (String name, String style, String style2)
    {
	this.nome = nome;
	this.estilo = style;
	this.estiloSec = style2;
    }

    public void addAlbum (Album musicAlbum)
    {
	this.albuns.add(musicAlbum);
    }
}

class Album extends Elemento
{
    ArrayList<Musica> musicas;
    String nome, banda, estilo;

    public void criaAlbum (String name, String band, String style)
    {
	this.nome = name;
	this.banda = band;
	this.estilo = style;
    }

    public void addMusica (Musica music)
    {
	this.musicas.dd(music);
    }
}

class Musica extends Elemento
{
    int duracao; // em segundos
    String nome, estilo;

    public void criaMusica (int dur, String name, String style)
    {
	this.duracao = dur;
	this.nome = name;
	this.estilo = style;
    }

}


interface Visitor
{
    void visitaTodos(Elemento elemento);
    void visitaEstilo(Elemento elemento, String estilo);
    void visitaTempo (Elemento elemento, String estilo, int tempo);
}

class Visitante implements Visitor
{
    public void visitaTodos(Elemento elemento)
    {
	System.out.println("Nome: ", +elemento.nome, " & Estilo: ", +elemento.estilo);			   
    }

    public void visitaEstilo (Elemento elemento, String estilo)
    {
	System.out.println("Nome: ", +elemento.nome, " & Estilo: ", +elemento.estilo);
	    
    }
    
    public void visitaTempo (Elemento elemento, String estilo, int tempo)
    {
       System.out.println("Nome: ", +elemento.nome, " & Estilo: ", +elemento.estilo);
    }
    
}
