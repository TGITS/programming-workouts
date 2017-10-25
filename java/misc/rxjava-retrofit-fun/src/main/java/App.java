import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.reactivestreams.Subscription;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import org.reactivestreams.Subscriber;

import java.util.List;

public class App {

    public static void main(String[] args) {
        listRepos("octocat");
    }

    public static void listRepos(String user) {
        retrofit2.Retrofit retrofit =
                new retrofit2.Retrofit.Builder()
                        .baseUrl("https://api.github.com/")
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        GitHubService service = retrofit.create(GitHubService.class);
        service.listRepos(user)
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onComplete() {
                        System.out.println("Sequence completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("Subscribe !");
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        for (Repo repo : repos) {
                            System.out.println("Repo: " + repo.getName());
                        }
                    }
                });
    }
}
