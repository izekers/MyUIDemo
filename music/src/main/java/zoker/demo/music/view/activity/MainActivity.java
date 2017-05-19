package zoker.demo.music.view.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.IBinder;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.zekers.utils.CommonUtils;
import com.zekers.utils.permission.PermissionCallback;
import com.zekers.utils.permission.PermissionManager;
import com.zoker.base.BaseAbility;

import zoker.demo.music.Constants;
import zoker.demo.music.R;
import zoker.demo.music.databinding.ActivityMainBinding;
import zoker.demo.music.databinding.MainNavHeaderBinding;
import zoker.demo.music.view.fragment.MainFragment;

public class MainActivity extends BaseAbility implements ServiceConnection{
    private ActivityMainBinding binding;
    private MainNavHeaderBinding navHeaderBinding;
    private String action;

    private final PermissionCallback permissionReadstorageCallback = new PermissionCallback() {
        @Override
        public void permissionGranted() {
            loadEverything();
        }

        @Override
        public void permissionRefused() {
            finish();
        }
    };


    private Runnable navigateLibrary = new Runnable() {
        public void run() {
            binding.navView.getMenu().findItem(R.id.nav_library).setChecked(true);
            Fragment fragment = MainFragment.newInstance(Constants.PLAYLIST_TYPE.ALLSONG);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_view, fragment).commitAllowingStateLoss();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupNavHeader(binding.navView);

        binding.navView.postDelayed(new Runnable() {
            @Override
            public void run() {
                setupDrawerContent(binding.navView);
                setupNavigationIcons(binding.navView);
            }
        }, 700);

        //检测权限
        if (CommonUtils.isMarshmallow()) {
            checkPermissionAndThenLoad();
        } else {
            loadEverything();
        }
    }

    //6.0加入的权限判断
    private void checkPermissionAndThenLoad() {
//        //check for permission
        if (PermissionManager.checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            loadEverything();
        } else {
            if (PermissionManager.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Snackbar.make(binding.mainView.mainView, "Listener will need to read external storage to display songs on your device.",
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                PermissionManager.askForPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE, permissionReadstorageCallback);
                            }
                        }).show();
            } else {
                PermissionManager.askForPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, permissionReadstorageCallback);
            }
        }
    }

    private void loadEverything() {
        Runnable navigation = navigateLibrary;

        if (navigation != null) {
            navigation.run();
        } else {
            navigateLibrary.run();
        }

//        new initQuickControls().execute("");
    }

    //页面显示相关
    private void setupNavHeader(NavigationView navigationView){
        if (navHeaderBinding==null){
            navHeaderBinding=MainNavHeaderBinding.inflate((LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE));
        }else {
            navigationView.removeHeaderView(navHeaderBinding.navHeader);
        }
        binding.navView.addHeaderView(navHeaderBinding.navHeader);
    }

    /**
     * 设置左侧菜单图标
     * @param navigationView
     */
    private void setupNavigationIcons(NavigationView navigationView) {
        navigationView.getMenu().findItem(R.id.nav_library).setIcon(R.drawable.ic_music_note_white_48dp);
        navigationView.getMenu().findItem(R.id.nav_playlists).setIcon(R.drawable.ic_queue_music_white_48dp);
        navigationView.getMenu().findItem(R.id.nav_folders).setIcon(R.drawable.ic_folder_white_48dp);
        navigationView.getMenu().findItem(R.id.nav_favourate).setIcon(R.drawable.ic_favorite_white_48dp);
        navigationView.getMenu().findItem(R.id.nav_recent_play).setIcon(R.drawable.ic_watch_later_white_48dp);
        navigationView.getMenu().findItem(R.id.nav_recent_add).setIcon(R.drawable.ic_add_box_white_48dp);
        navigationView.getMenu().findItem(R.id.nav_play_ranking).setIcon(R.drawable.ic_sort_white_48dp);
        navigationView.getMenu().findItem(R.id.nav_settings).setIcon(R.drawable.ic_settings_white_48dp);
        navigationView.getMenu().findItem(R.id.nav_exit).setIcon(R.drawable.ic_exit_to_app_white_48dp);

    }

    /**
     * 导航
     * @param menuItem
     */
    private void updatePosition(final MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_library:
                break;
            case R.id.nav_playlists:
                break;
            case R.id.nav_folders:
                break;
            case R.id.nav_favourate:
                break;
            case R.id.nav_recent_play:
                break;
            case R.id.nav_recent_add:
                break;
            case R.id.nav_play_ranking:
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_exit:
                this.finish();
                break;
        }

        binding.drawerLayout.closeDrawers();
    }

    //加入监听
    private void addBackstackListener() {
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                getSupportFragmentManager().findFragmentById(R.id.main_view).onResume();
            }
        });
    }

    /**
     * 监听menu点击
     * @param navigationView
     */
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(final MenuItem menuItem) {
                        updatePosition(menuItem);
                        return true;
                    }
                });
    }

    /**
     * ToolBar左侧按钮监听
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{//home键
                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
