# BitmapToPart
图片转换为粒子的动画

3.0 之后的动哈

1：
Animator->ValueAnimator->ObjectAnimator 新的动画格式
ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "alpha", 1f, 0f, 1f);
animator.setDuration(5000);
animator.start();
最基本的，可以在 AnimatorDemo里面查看的
2：
自定义动画的参数 
ValueAnimator.ofObject
PointEvaluator implements TypeEvaluator 可以进行自定义的加载
public Object evaluate(float fraction, Object startValue, Object endValue) 
也可以设置自定义的时间函数
anim.setInterpolator(new mTimeInterpolator());
AnimatorSet animSet = new AnimatorSet();
animSet.play(anim).with(anim2);
组合动画设置
3：碎裂的像素点
mParticle 每个小点的原型
点坐标
float cx; 
float cy;
半径
float radius;
颜色
int color;
透明度
float alpha;
矩形
Rect mBound;

自定义 一个View  
在里面 ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f); 
设置监听 通过时间戳来判定 具体的进度


4：NewPointView
ViewGroup rootView = (ViewGroup) ((Activity) (context)).findViewById(Window.ID_ANDROID_CONTENT);
ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
rootView.addView(this, lp);
rootView 根目录上面添加 自定义的View


