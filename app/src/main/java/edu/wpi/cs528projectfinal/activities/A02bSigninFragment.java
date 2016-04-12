 package edu.wpi.cs528projectfinal.activities;

    import android.os.AsyncTask;
    import android.os.Bundle;
    import android.support.v4.app.Fragment;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import org.apache.http.HttpResponse;
    import org.apache.http.client.HttpClient;
    import org.apache.http.client.methods.HttpGet;
    import org.apache.http.impl.client.DefaultHttpClient;

    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.io.OutputStreamWriter;
    import java.lang.ref.WeakReference;
    import java.net.URI;
    import java.net.URL;
    import java.net.URLConnection;
    import java.net.URLEncoder;

    import edu.wpi.cs528projectfinal.R;

    /**
     * Created by Chris on 3/26/2016.
     * Reference
     * http://www.tutorialspoint.com/android/android_php_mysql.htm
     * http://www.michenux.net/android-asynctask-in-fragment-best-pratices-725.html
     */
    public class A02bSigninFragment extends Fragment {
        private String TAG = this.getClass().toString();

        private TextView statusField,roleField;
     //   private Context context;
        private int byGetOrPost = 0;

       // The asyncTask is declared as a member of the fragment. A weak reference is used so that the fragment and the async task are loosely coupled
        private WeakReference<MyAsyncTask> asyncTaskWeakRef;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.v(TAG, "onCreate");
            setHasOptionsMenu(false);

            // In the onCreate method, we configure the fragment instance to be retained on configuration change. Then, we start the async task.
            setRetainInstance(true);
            startNewAsyncTask();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.v(TAG, "onCreateView");
            View view = inflater.inflate(R.layout.a02a_fragment_login, container, false);

            this.statusField = (TextView)view.findViewById(R.id.textView6);
            this.roleField = (TextView)view.findViewById(R.id.textView7);
            byGetOrPost = /* flag; */ 0; // TODO flag wants to be an "extra" in the intent

            updateUI();

            return view;
        }

        private void updateUI() {
            // nothing to do
        }

        // todo - need to update the results when async task hits some case ... (login successful)
        protected void showResults(String result){
            this.statusField.setText("Login Successful");
            this.roleField.setText(result);
        }

        // the method to start the async task
        private void startNewAsyncTask() {
            MyAsyncTask asyncTask = new MyAsyncTask(this);
            this.asyncTaskWeakRef = new WeakReference<MyAsyncTask >(asyncTask );
            asyncTask.execute();
        }

        // This method may be useful to test if the async task is running or not :
        private boolean isAsyncTaskPendingOrRunning() {
            return this.asyncTaskWeakRef != null &&
                    this.asyncTaskWeakRef.get() != null &&
                    !this.asyncTaskWeakRef.get().getStatus().equals(AsyncTask.Status.FINISHED);
        }

        // here’s the template code for the async task. This is an inner class of the fragment.
        private static class MyAsyncTask extends AsyncTask<Void, Void, Void> {

            private WeakReference<A02bSigninFragment> fragmentWeakRef;

            private MyAsyncTask (A02bSigninFragment fragment) {
                this.fragmentWeakRef = new WeakReference<A02bSigninFragment>(fragment);
            }



            @Override
            protected Void doInBackground(Void... params) {
                // zona TODO remove this
                return null;
                /*
                if(GetOrPost ==0){ //means by Get Method

                    try{
                        String username = (String)arg0[0];
                        String password = (String)arg0[1];
                        String link = "http://myphpmysqlweb.hostei.com/login.php?username="+username+"& password="+password;

                        URL url = new URL(link);
                        HttpClient client = new DefaultHttpClient();
                        HttpGet request = new HttpGet();
                        request.setURI(new URI(link));
                        HttpResponse response = client.execute(request);
                        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                        StringBuffer sb = new StringBuffer("");
                        String line="";

                        while ((line = in.readLine()) != null) {
                            sb.append(line);
                            break;
                        }
                        in.close();
                        return sb.toString();
                    }

                    catch(Exception e){
                        return new String("Exception: " + e.getMessage());
                    }
                }
                else{
                    try{
                        String username = (String)arg0[0];
                        String password = (String)arg0[1];

                        String link="http://myphpmysqlweb.hostei.com/loginpost.php";
                        String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                        data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                        URL url = new URL(link);
                        URLConnection conn = url.openConnection();

                        conn.setDoOutput(true);
                        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                        wr.write( data );
                        wr.flush();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        StringBuilder sb = new StringBuilder();
                        String line = null;

                        // Read Server Response
                        while((line = reader.readLine()) != null)
                        {
                            sb.append(line);
                            break;
                        }
                        return sb.toString();
                    }
                    catch(Exception e){
                        return new String("Exception: " + e.getMessage());
                    }
                }
                */
            }

            @Override
            protected void onPostExecute(Void response) {
                super.onPostExecute(response);
                if (this.fragmentWeakRef.get() != null) {
                    //TODO: treat the result
                    //this.statusField.setText("Login Successful");
                    //this.roleField.setText(result);
                    int i= 5/0;  // TODO treat the result
                }
            }
        }

    }


