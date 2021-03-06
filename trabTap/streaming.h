#include <stdio.h>
#include <string.h>

#define STRING_MAX 20
#define ALBUM_MAX 5
#define MUSICA_MAX 5

class Streaming  //Singleton patter
{
  
  private:	
    Streaming();
    static Streaming instance=NULL;
    
  public:
    Streaming instanceStraming(string, string); //instancia uma
    char password[STRING_MAX]; 	//Senha
    char userLogin[STRING_MAX];	//Login 
    int usetType; 		//Plano
    Diretorio directory; //Diretorio de musicas
    
    
};

class Diretorio //Composite Visito
{
    Playlist playlists[ALBUM_MAX]={NULL};
    char estiloPesquisa[STRING_MAX];
};

class Playlist
{
  char nome[STRING_MAX], estilo[STRING_MAX], estiloSec[STRING_MAX];
  Playlist playlists[ALBUM_MAX]={NULL}; 
  Album albuns[ALBUM_MAX]={NULL};
};

class Album //Composite Composite
{
 public:
  Musica musicas[MUSICA_MAX];
  char nome[STRING_MAX], banda[STRING_MAX], estilo[STRING_MAX];
};

class Musica
{
  int duracao ;//segundos 
  char nome[STRING_MAX];
  char estilo[STRING_MAX];
  Album* albumPai;
};

class Visitor
{
 public:
  virtual void visitar(Diretorio diretorio);  
};

class Todos : Visitor
{
  void visitar(Diretorio diretorio) override{
      for (int i=0; diretorio.playlists[i]!=NULL; i++){
	  printf ("P: %s\n", diretorio.playlists[i].nome);
	    for (int j=0; diretorio.playlists[i].albuns[j]!=NULL; j++){
	      printf("A: %s\n", diretorio.playlists[i].albuns[j].nome);
		for (int k=0; k < 5; k++)
		  printf ("M: %s\n", diretorio.playlists[i].albuns[j].musicas[k].nome); 
	    }
	}
    }
};

class Estilos : Visitor
{
    void visitar(Diretorio diretorio){
      for (int i=0; diretorio.playlists[i]!=NULL; i++)
	for (int j=0; diretorio.playlists[i].albuns[j]!=NULL;j++)
	  for (int k=0; k < 5; k++)
	    if (strcmp(diretorio.playlists[i].albuns[j].musicas[k].nome,diretorio.estiloPesquisa))
	      printf("%s\n", diretorio.playlists[i].albuns[j].musicas[k].nome);
    }
    
};

class Tempo : Visitor
{
  void visitar(Diretorio diretorio){
    for (int i=0; diretorio.playlists[i]!=NULL; i++)
      if (strcmp(diretorio.playlists[i].estilo,diretorio.estiloPesquisa))
	{
	  printf("Playlist %s com o estilo %s\n", diretorio.playlists[i].nome, diretorio.playlists[i].estilo);
	  for (int j=0; diretorio.playlists[i].albuns[j]!=NULL;j++)
	    if (strcmp(diretorio.playlists[i].albuns[j].estilo, diretorio.estiloPesquisa))
	      for (int k=0; diretorio.playlists[i].albuns[j].musicas[k]!=NULL;k++)
		if (diretorio.playlists[i].albuns[j].musicas[k].duracao > 180)
		  printf("Musica %s com o estilo %s", diretorio.playlists[i].albuns[j].musicas[k].nome, diretorio.playlists[i].albuns[j].musicas[k].estilo)
		    }
    
      else if (strcmp(diretorio.playlists[i].estiloSec,diretorio.estiloPesquisa))
	{
	  printf ("Playlist %s com o estilo secundário %s\n", diretorio.playlists[i].nome, diretorio.playlists[i].estiloSec);
	  for (int j=0; diretorio.playlists[i].albuns[j]!=NULL;j++)
	    if (strcmp(diretorio.playlists[i].albuns[j].estilo, diretorio.estiloPesquisa))
	      for (int k=0; diretorio.playlists[i].albuns[j].musicas[k]!=NULL;k++)
		if (diretorio.playlists[i].albuns[j].musicas[k].duracao > 180)
		  printf("Musica %s com o estilo %s", diretorio.playlists[i].albuns[j].musicas[k].nome, diretorio.playlists[i].albuns[j].musicas[k].estilo)
		    }
  }
}
