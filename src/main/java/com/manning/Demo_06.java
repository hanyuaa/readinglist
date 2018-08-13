package com.manning;

import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Demo_06 {

    private static final String HOST = "192.168.172.3";
    private static final int PORT = 9200;

    public static void main(String[] args) throws Exception {

    }

    public static void bulkRequest() throws IOException {
        new ByteSizeValue(12L);

    }

    public static void updateRequest() throws IOException {

    }

    public static void getRequest() throws IOException {
        RestHighLevelClient client = getClient();
        GetRequest getRequest = new GetRequest("join_index", "doc", "1");
//        FetchSourceContext fetchSourceContext = new FetchSourceContext(false);

        GetResponse documentFields = client.get(getRequest);
        DocumentField user = documentFields.getField("text");
        Object value = user.getValue();
        System.out.println(value.toString());

        UpdateRequest updateRequest = new UpdateRequest("join_index", "doc", "1");
        client.close();
    }

    public static void indexRequest() {
        IndexRequest request = new IndexRequest(
                "posts",
                "doc",
                "1");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(jsonString, XContentType.JSON);

        Map<String, Object> map = new HashMap<>();
        map.put("user", "kimchy");
        map.put("postDate", new Date());
        map.put("message", "trying out Elasticsearch");
        IndexRequest indexRequest = new IndexRequest("posts", "doc", "1").source(map);

        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        request.versionType(VersionType.EXTERNAL);
        request.opType(DocWriteRequest.OpType.CREATE);
    }

    /**
     * 删除索引
     */
    public static void deleteIndex() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(HOST, PORT, "http")));
        DeleteIndexRequest request = new DeleteIndexRequest("posts");

        request.timeout(TimeValue.timeValueMinutes(2));
        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
        request.indicesOptions(IndicesOptions.lenientExpandOpen());

        DeleteIndexResponse response = client.indices().delete(request);
        boolean acknowledged = response.isAcknowledged();
        System.out.println(acknowledged);

        client.close();
    }

    /**
     * 创建索引
     */
    public static void createIndex() throws IOException {
        // 设置集群名称
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(HOST, PORT, "http")));

        CreateIndexRequest request = new CreateIndexRequest("twitter");
        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2)
        );

        request.mapping("tweet",
                "  {\n" +
                        "    \"tweet\": {\n" +
                        "      \"properties\": {\n" +
                        "        \"message\": {\n" +
                        "          \"type\": \"text\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }",
                XContentType.JSON);

        request.alias(
                new Alias("twitter_alias")
        );

        request.timeout(TimeValue.timeValueMinutes(2));
        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
        request.waitForActiveShards(ActiveShardCount.DEFAULT);

        CreateIndexResponse createIndexResponse = client.indices().create(request);

        boolean acknowledged = createIndexResponse.isAcknowledged();
        boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();

        System.out.println(acknowledged);
        System.out.println(shardsAcknowledged);
        // 关闭client
        client.close();
    }

    public static RestHighLevelClient getClient() {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(HOST, PORT, "http")));

        return client;
    }
}
