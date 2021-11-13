# NavigationAndDialogFragment
Android ナビゲーショングラフに Dialogを追加する。 
* ボタンを押したらダイアログを開く
* YES / NOボタンを押したら、それに対応する処理を Fragmentで実行する

![device-2021-11-13-231929](https://user-images.githubusercontent.com/750091/141647339-e3f63a4a-3bb6-4610-a106-04bdf1d6b653.gif)



## 解説
### ナビゲーショングラフ
```
    <fragment
        android:id="@+id/mainFragment"
        android:name="org.nunocky.navigationanddialogfragment.MainFragment"
        android:label="MainFragment">
        <action
            android:id="@+id/action_mainFragment_to_yesNoDialogFragment"
            app:destination="@id/yesNoDialogFragment" />
    </fragment>
    <dialog
        android:id="@+id/yesNoDialogFragment"
        android:name="org.nunocky.navigationanddialogfragment.YesNoDialogFragment"
        android:label="YesNoDialogFragment" />
```

<img width="381" alt="スクリーンショット 2021-11-13 午後10 12 36" src="https://user-images.githubusercontent.com/750091/141646235-80f46064-5c1c-42e7-8543-444bce78e376.png">


### フラグメントからダイアログを開く

```
    private fun showDialog() {
        findNavController().navigate(R.id.yesNoDialogFragment)
    }
```

### ダイアログの Yes/Noの結果

まず返り値のクラスを作る

```
    sealed class Result {
        object Init : Result()
        object Yes : Result()
        object No : Result()
    }
```

この返り値を Fragmentと Dialogで共有する。そのために Activityスコープの ViewModelを使う。

ViewModelの定義
```
class DialogResultViewModel : ViewModel() {
    val result = MutableLiveData<YesNoDialogFragment.Result>(YesNoDialogFragment.Result.Init)
}
```

ViewModelを Fragmentと Dialogで共有する。
```
    private val resultViewModel: DialogResultViewModel by activityViewModels()
```

Fragmentは viewmodel.resultを observeし、ボタン押下時の挙動を実装する。

```
        resultViewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                YesNoDialogFragment.Result.Yes -> {
                    Toast.makeText(requireActivity(), "YES", Toast.LENGTH_SHORT).show()
                }
                YesNoDialogFragment.Result.No -> {
                    Toast.makeText(requireActivity(), "NO", Toast.LENGTH_SHORT).show()
                }
                else -> {
                }
            }
        }
```

## 感想

__個人的にはこの実装方法は好きではない。むしろ推奨しない。__

一番の理由は YesNoDialogを使い回すことができないから。 Android Navigationは、 この例のように汎用的に用いられるダイアログが再利用されることを考慮していない。 
再利用性の観点で言えば従来のコールバックを用いる方法のほうが良い。コードも読みやすいし。

結果を viewModelで共有するのも気に入らない。 viewModelを宣言してしまえば他の Fragmentでも結果の変化が見えてしまうというのが気持ち悪い。 この方法が推奨されるというより、今の Navigationの仕組みでは結果を前画面に戻せないので仕方なくこの方法を使っている感がある、

なので個人的にはダイアログは従来のコールバックを用いる方法を使い続けると思う
