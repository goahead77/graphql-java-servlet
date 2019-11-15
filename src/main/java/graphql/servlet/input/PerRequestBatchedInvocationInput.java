package graphql.servlet.input;

import graphql.schema.GraphQLSchema;
import graphql.servlet.context.ContextSetting;
import graphql.servlet.context.GraphQLContext;
import graphql.servlet.core.internal.GraphQLRequest;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * A collection of GraphQLSingleInvocationInputs that share a context object.
 */
@Getter
public class PerRequestBatchedInvocationInput implements GraphQLBatchedInvocationInput {

    private final List<GraphQLSingleInvocationInput> executionInputs;
    private final ContextSetting contextSetting;

    public PerRequestBatchedInvocationInput(List<GraphQLRequest> requests, GraphQLSchema schema, Supplier<GraphQLContext> contextSupplier, Object root, ContextSetting contextSetting) {
        GraphQLContext context = contextSupplier.get();
        executionInputs = requests.stream().map(request -> new GraphQLSingleInvocationInput(request, schema, context, root)).collect(Collectors.toList());
        this.contextSetting = contextSetting;
    }

}