export class Thread {
    noOfLikes: number;
    title: string;
    timeOfPost: Date;
    content: string;

    postId: string;
    userId: string;
    courseId: string;
    comments: Set<Thread>;


    constructor(postId: string, userId: string, courseId: string, timeOfPost: Date, content: string, title?: string) {
        this.title = title;
        this.timeOfPost = timeOfPost;
        this.content = content;
        this.postId = postId;
        this.userId = userId;
        this.courseId = courseId;
        this.noOfLikes = 10;
        this.comments = new Set<Thread>();
    }
}
