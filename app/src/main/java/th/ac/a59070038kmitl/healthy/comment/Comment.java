package th.ac.a59070038kmitl.healthy.comment;

public class Comment {
    String name, email, body;
    int postId, id;

    public Comment(String name, String email, String body, int postId, int id) {
        this.name = name;
        this.email = email;
        this.body = body;
        this.postId = postId;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }
}
