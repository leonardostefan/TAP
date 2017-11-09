
interface Visitor
{
 
  public void visitar(Diretorio diretorio);  
}



class Todos implements Visitor
{
   void visitar(Diretorio diretorio) override{
      for (int i=0; diretorio.playlistssize(); i++){
         System.out.printf ("P: %s\n", diretorio.playlists[i].nome);
         for (int j=0; diretorio.playlists[i].albuns[j].size(); j++){
            System.out.printf("A: %s\n", diretorio.playlists[i].albuns[j].nome);
            for (int k=0; k < 5; k++){
               System.out.printf ("M: %s\n", diretorio.playlists[i].albuns[j].musicas[k].nome); 
            }
         }
      }
    }
}

class Estilos implements Visitor
{
    void visitar(Diretorio diretorio){
      for (int i=0; diretorio.playlists.size(); i++){
         for (int j=0; diretorio.playlists[i].albuns[j].size();j++){
            for (int k=0; k < 5; k++){
               if (diretorio.playlists[i].albuns[j].musicas[k].nome.equals(diretorio.estiloPesquisa)){
                  System.out.printf("%s\n", diretorio.playlists[i].albuns[j].musicas[k].nome);
               }
            }  
         }
      }
   }  
}

class Tempo implements Visitor
{
   void visitar(Diretorio diretorio){
      for (int i=0; diretorio.playlists[i].size(); i++){
         if (diretorio.playlists[i].estilo.equals(diretorio.estiloPesquisa)){
            System.out.printf("Playlist %s com o estilo %s\n", diretorio.playlists[i].nome, diretorio.playlists[i].estilo);
            for (int j=0; diretorio.playlists[i].albuns[j].size();j++){
               if (diretorio.playlists[i].albuns[j].estilo.equals(diretorio.estiloPesquisa)){
                  for (int k=0; diretorio.playlists[i].albuns[j].musicas[k].size();k++){
                     if (diretorio.playlists[i].albuns[j].musicas[k].duracao > 180){
                        System.out.printf("Musica %s com o estilo %s", diretorio.playlists[i].albuns[j].musicas[k].nome, diretorio.playlists[i].albuns[j].musicas[k].estilo)
                     }
                  }
               }
            }
         }
         else if (diretorio.playlists[i].estiloSec.equals(diretorio.estiloPesquisa)){
            System.out.printf ("Playlist %s com o estilo secundário %s\n", diretorio.playlists[i].nome, diretorio.playlists[i].estiloSec);
            for (int j=0; diretorio.playlists[i].albuns[j].size();j++){
               if (diretorio.playlists[i].albuns[j].estilo.equals(diretorio.estiloPesquisa)){
                  for (int k=0; diretorio.playlists[i].albuns[j].musicas[k].size();k++){
                     if (diretorio.playlists[i].albuns[j].musicas[k].duracao > 180){
                        System.out.printf("Musica %s com o estilo %s", diretorio.playlists[i].albuns[j].musicas[k].nome, diretorio.playlists[i].albuns[j].musicas[k].estilo)
                     }
                  }
               }
            }
         }
      }
   }
}
