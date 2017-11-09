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

class Diretorio
{
    ArrayList<Playlist> playlists;
    private String estiloPesquisa;

    public void addPlaylist (Playlist lista)
    {
	this.playlists.add(lista);
    }

    public void accept
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

class Album extends Diretorio
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

class Musica
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
    void visitaTodos(

}
