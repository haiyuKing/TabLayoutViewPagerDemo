package com.why.project.tablayoutviewpagerdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.why.project.tablayoutviewpagerdemo.adapter.ContentPagerAdapter;
import com.why.project.tablayoutviewpagerdemo.fragment.WebViewFragment;
import com.why.project.tablayoutviewpagerdemo.model.TabItemModel;
import com.why.project.tablayoutviewpagerdemo.viewpager.MyCustomViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private TabLayout mTabLayout;
	private MyCustomViewPager mTabViewPager;

	private List<TabItemModel> tabIndicators;
	private List<Fragment> tabFragments;

	private ContentPagerAdapter contentAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		initDatas();
		initEvents();

	}

	private void initViews() {
		mTabLayout = (TabLayout) findViewById(R.id.tl_top);
		mTabViewPager = (MyCustomViewPager) findViewById(R.id.vp_tab);
		mTabViewPager.setOffscreenPageLimit(3);//禁止预加载【如果想要延迟首个选项卡的销毁时间，那么就需要设置这个数值高点】
	}

	private void initDatas() {
		//初始化选项卡子项的文本、超链接model集合
		tabIndicators = new ArrayList<TabItemModel>();
		tabIndicators.add(new TabItemModel("百度","http://www.baidu.com"));
		tabIndicators.add(new TabItemModel("CSDN","http://blog.csdn.net"));
		tabIndicators.add(new TabItemModel("博客园","http://www.cnblogs.com"));
		tabIndicators.add(new TabItemModel("极客头条","http://geek.csdn.net/mobile"));
		tabIndicators.add(new TabItemModel("优设","http://www.uisdc.com/"));
		tabIndicators.add(new TabItemModel("玩Android","http://www.wanandroid.com/index"));
		tabIndicators.add(new TabItemModel("掘金","https://juejin.im/"));

		//初始化碎片集合
		tabFragments = new ArrayList<>();

		for(int i=0;i<tabIndicators.size();i++){
			TabItemModel tabItemModel = tabIndicators.get(i);

			Bundle bundle = new Bundle();
			bundle.putString("param", tabItemModel.getTabUrl());
			tabFragments.add(WebViewFragment.getInstance(WebViewFragment.class,bundle));
		}
		//实例化Adapter
		contentAdapter = new ContentPagerAdapter(getSupportFragmentManager(),tabIndicators,tabFragments);
		mTabViewPager.setAdapter(contentAdapter);
		//TabLayout和ViewPager相关联
		mTabLayout.setupWithViewPager(mTabViewPager);
	}

	private void initEvents() {
		mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				//选中了tab的逻辑
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
				//未选中了tab的逻辑
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {
				//再次选中了tab的逻辑
			}
		});
	}
}
