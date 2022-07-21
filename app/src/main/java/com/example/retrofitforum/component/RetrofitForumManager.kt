package com.example.retrofitforum.component
import androidx.lifecycle.MutableLiveData
import org.koin.core.annotation.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Single
class RetrofitForumManager: ForumManager
{
    override val monitored: MutableLiveData<ForumPost> by lazy {
        MutableLiveData<ForumPost>(ForumPost(0, 0, "Title", "Body"))
    }

    override val list: MutableLiveData<ArrayList<ForumPost>> by lazy {
        MutableLiveData<ArrayList<ForumPost>>(ArrayList())
    }

    override val state: MutableLiveData<ForumManager.State> by lazy {
        MutableLiveData<ForumManager.State>(ForumManager.State.IDLE)
    }

    private val service = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ForumPostService::class.java)

    override fun getAll() {
        setLoading()
        service.getAll().enqueue( object : Callback<ArrayList<ForumPost>>
            {
                override fun onResponse(
                    call: Call<ArrayList<ForumPost>>,
                    response: Response<ArrayList<ForumPost>>
                ) {
                    validateBody(response.body()) { list.value = it }
                }

                override fun onFailure(call: Call<ArrayList<ForumPost>>, t: Throwable) { setError() }
            }
        )
    }

    override fun get(postId: Long) {
        setLoading()
        service.get(postId)
            .enqueue(
                object : Callback<ForumPost>
                {
                    override fun onResponse(call: Call<ForumPost>, response: Response<ForumPost>)
                    {
                        validateBody(response.body()) { monitored.value = it }
                    }

                    override fun onFailure(call: Call<ForumPost>, t: Throwable)
                    {
                        setError()
                    }

                }
            )
    }

    override fun add(post: CreateForumPostBody) {
        setLoading()
        service.post(post)
            .enqueue(
                object : Callback<ForumPost>
                {
                    override fun onResponse(call: Call<ForumPost>, response: Response<ForumPost>)
                    {
                        validateBody(response.body()) {
                            monitored.value = it
                            list.value!!.add(it)
                            list.forceRefresh()
                        }
                    }

                    override fun onFailure(call: Call<ForumPost>, t: Throwable)
                    {
                        setError()
                    }

                }
            )
    }

    override fun edit(post: PatchForumPostBody) {
        setLoading()
        service.patch(post, post.id)
            .enqueue(
                object : Callback<ForumPost>
                {
                    override fun onResponse(call: Call<ForumPost>, response: Response<ForumPost>)
                    {
                        validateBody(response.body()) { post ->
                            monitored.value = post
                            val index = list.value!!.indexOf(list.value!!.find { it.id == post.id })
                            list.value!![index] = post
                            list.forceRefresh()
                        }
                    }

                    override fun onFailure(call: Call<ForumPost>, t: Throwable)
                    {
                        setError()
                    }

                }
            )
    }

    override fun delete(post: ForumPost) {
        setLoading()
        service.delete(post.id)
            .enqueue(
                object : Callback<EmptyResponse>
                {
                    override fun onResponse(call: Call<EmptyResponse>, response: Response<EmptyResponse>)
                    {
                        validateBody(response.body()) {
                            monitored.value = ForumPost(0,0, "DELETED", "DELETED")
                            list.value = ArrayList((list.value!!.filter { it.id != post.id }))
                        }
                    }

                    override fun onFailure(call: Call<EmptyResponse>, t: Throwable)
                    {
                        setError()
                    }

                }
            )
    }

    override fun filter(userId: Int) {
        val filtered = (list.value!!.filter { it.userId == userId })
        list.value = ArrayList(filtered)
    }

    private fun setIdle() { state.value = ForumManager.State.IDLE }
    private fun setLoading() { state.value = ForumManager.State.LOADING }
    private fun setError() { state.value = ForumManager.State.ERROR }

    private fun <T>validateBody(body: T?, onNotNull: (T)->Unit)
    {
        if(body != null) {
            setIdle()
            onNotNull(body)
        } else {
            setError()
        }
    }

}