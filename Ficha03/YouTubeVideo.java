import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

public class YouTubeVideo {
    private String nome;
    private String conteudo;
    private LocalDate upload;
    private int resX;
    private int resY;
    private LocalTime duracao;
    private String[] comments;
    private int nComments;
    private int likes;
    private int dislikes;

    public YouTubeVideo() {
        this.nome = "";
        this.conteudo = "";
        this.upload = LocalDate.of(0,0,0);
        this.resX = 0;
        this.resY = 0;
        this.duracao = LocalTime.of(0,0);
        this.comments = null;
        this.nComments = 0;
        this.likes = 0;
        this.dislikes = 0;
    }

    public YouTubeVideo(String nome, String conteudo, LocalDate upload, int resX, int resY, LocalTime duracao, String[] comments, int nComments, int likes, int dislikes) {
        this.nome = nome;
        this.conteudo = conteudo;
        this.upload = upload;
        this.resX = resX;
        this.resY = resY;
        this.duracao = duracao;
        this.comments = comments;
        this.nComments = nComments;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public YouTubeVideo (YouTubeVideo video){
        this.nome = video.getNome();
        this.conteudo = video.getConteudo();
        this.upload = video.getUpload();
        this.resX = video.getResX();
        this.resY = video.getResY();
        this.duracao = video.getDuracao();
        this.comments = video.getComments();
        this.nComments = video.getnComments();
        this.likes = video.getLikes();
        this.dislikes = video.getDislikes();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDate getUpload() {
        return upload;
    }

    public void setUpload(LocalDate upload) {
        this.upload = upload;
    }

    public int getResX() {
        return resX;
    }

    public void setResX(int resX) {
        this.resX = resX;
    }

    public int getResY() {
        return resY;
    }

    public void setResY(int resY) {
        this.resY = resY;
    }

    public LocalTime getDuracao() {
        return duracao;
    }

    public void setDuracao(LocalTime duracao) {
        this.duracao = duracao;
    }

    public String[] getComments() {
        return comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

    public int getnComments() {
        return nComments;
    }

    public void setnComments(int nComments) {
        this.nComments = nComments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YouTubeVideo that = (YouTubeVideo) o;
        return resX == that.resX && resY == that.resY && nComments == that.nComments && likes == that.likes && dislikes == that.dislikes && Objects.equals(nome, that.nome) && Objects.equals(conteudo, that.conteudo) && Objects.equals(upload, that.upload) && Objects.equals(duracao, that.duracao) && Arrays.equals(comments, that.comments);
    }

    public YouTubeVideo clone(){
        return new YouTubeVideo(this);
    }

    public String toString() {
        return "YouTubeVideo{" +
                "nome='" + nome + '\'' +
                ", conteudo='" + conteudo + '\'' +
                ", upload=" + upload +
                ", resX=" + resX +
                ", resY=" + resY +
                ", duracao=" + duracao +
                ", comments=" + Arrays.toString(comments) +
                ", nComments=" + nComments +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                '}';
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void insereComentario(String comm){
        if(this.nComments == this.comments.length){
            String arr[] = new String[this.nComments * 2];
            System.arraycopy(this.comments, 0, arr, 0, this.comments.length);
            this.setComments(arr);
        }
        String[] aux = getComments();
        aux[this.nComments] = comm;
        setComments(aux);
        setnComments(this.nComments + 1);
    }

    public long qtsDiasDepois(){
        LocalDate hoje = LocalDate.now();
        long days = Duration.between(this.upload,hoje).toDays();
        return days;
    }

    public void thumbsUp(){
        setLikes(this.likes + 1);
    }

    public String processa(){
        return this.conteudo;
    }


}
