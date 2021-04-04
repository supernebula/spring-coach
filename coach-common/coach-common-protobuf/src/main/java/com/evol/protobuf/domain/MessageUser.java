//// Generated by the protocol buffer compiler.  DO NOT EDIT!
//// source: com/evol/protobuf/user.proto
//
//package com.evol.protobuf.domain;
//
//public final class MessageUser {
//  private MessageUser() {}
//  public static void registerAllExtensions(
//      com.google.protobuf.ExtensionRegistryLite registry) {
//  }
//
//  public static void registerAllExtensions(
//      com.google.protobuf.ExtensionRegistry registry) {
//    registerAllExtensions(
//        (com.google.protobuf.ExtensionRegistryLite) registry);
//  }
//  public interface MessageUserLoginOrBuilder extends
//      // @@protoc_insertion_point(interface_extends:MessageUserLogin)
//      com.google.protobuf.MessageOrBuilder {
//
//    /**
//     * <code>string access_token = 1;</code>
//     * @return The accessToken.
//     */
//    java.lang.String getAccessToken();
//    /**
//     * <code>string access_token = 1;</code>
//     * @return The bytes for accessToken.
//     */
//    com.google.protobuf.ByteString
//        getAccessTokenBytes();
//
//    /**
//     * <code>string username = 2;</code>
//     * @return The username.
//     */
//    java.lang.String getUsername();
//    /**
//     * <code>string username = 2;</code>
//     * @return The bytes for username.
//     */
//    com.google.protobuf.ByteString
//        getUsernameBytes();
//  }
//  /**
//   * Protobuf type {@code MessageUserLogin}
//   */
//  public static final class MessageUserLogin extends
//      com.google.protobuf.GeneratedMessageV3 implements
//      // @@protoc_insertion_point(message_implements:MessageUserLogin)
//      MessageUserLoginOrBuilder {
//  private static final long serialVersionUID = 0L;
//    // Use MessageUserLogin.newBuilder() to construct.
//    private MessageUserLogin(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
//      super(builder);
//    }
//    private MessageUserLogin() {
//      accessToken_ = "";
//      username_ = "";
//    }
//
//    @java.lang.Override
//    @SuppressWarnings({"unused"})
//    protected java.lang.Object newInstance(
//        UnusedPrivateParameter unused) {
//      return new MessageUserLogin();
//    }
//
//    @java.lang.Override
//    public final com.google.protobuf.UnknownFieldSet
//    getUnknownFields() {
//      return this.unknownFields;
//    }
//    private MessageUserLogin(
//        com.google.protobuf.CodedInputStream input,
//        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//        throws com.google.protobuf.InvalidProtocolBufferException {
//      this();
//      if (extensionRegistry == null) {
//        throw new java.lang.NullPointerException();
//      }
//      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
//          com.google.protobuf.UnknownFieldSet.newBuilder();
//      try {
//        boolean done = false;
//        while (!done) {
//          int tag = input.readTag();
//          switch (tag) {
//            case 0:
//              done = true;
//              break;
//            case 10: {
//              java.lang.String s = input.readStringRequireUtf8();
//
//              accessToken_ = s;
//              break;
//            }
//            case 18: {
//              java.lang.String s = input.readStringRequireUtf8();
//
//              username_ = s;
//              break;
//            }
//            default: {
//              if (!parseUnknownField(
//                  input, unknownFields, extensionRegistry, tag)) {
//                done = true;
//              }
//              break;
//            }
//          }
//        }
//      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
//        throw e.setUnfinishedMessage(this);
//      } catch (java.io.IOException e) {
//        throw new com.google.protobuf.InvalidProtocolBufferException(
//            e).setUnfinishedMessage(this);
//      } finally {
//        this.unknownFields = unknownFields.build();
//        makeExtensionsImmutable();
//      }
//    }
//    public static final com.google.protobuf.Descriptors.Descriptor
//        getDescriptor() {
//      return com.evol.protobuf.domain.MessageUser.internal_static_MessageUserLogin_descriptor;
//    }
//
//    @java.lang.Override
//    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
//        internalGetFieldAccessorTable() {
//      return com.evol.protobuf.domain.MessageUser.internal_static_MessageUserLogin_fieldAccessorTable
//          .ensureFieldAccessorsInitialized(
//              com.evol.protobuf.domain.MessageUser.MessageUserLogin.class, com.evol.protobuf.domain.MessageUser.MessageUserLogin.Builder.class);
//    }
//
//    public static final int ACCESS_TOKEN_FIELD_NUMBER = 1;
//    private volatile java.lang.Object accessToken_;
//    /**
//     * <code>string access_token = 1;</code>
//     * @return The accessToken.
//     */
//    @java.lang.Override
//    public java.lang.String getAccessToken() {
//      java.lang.Object ref = accessToken_;
//      if (ref instanceof java.lang.String) {
//        return (java.lang.String) ref;
//      } else {
//        com.google.protobuf.ByteString bs =
//            (com.google.protobuf.ByteString) ref;
//        java.lang.String s = bs.toStringUtf8();
//        accessToken_ = s;
//        return s;
//      }
//    }
//    /**
//     * <code>string access_token = 1;</code>
//     * @return The bytes for accessToken.
//     */
//    @java.lang.Override
//    public com.google.protobuf.ByteString
//        getAccessTokenBytes() {
//      java.lang.Object ref = accessToken_;
//      if (ref instanceof java.lang.String) {
//        com.google.protobuf.ByteString b =
//            com.google.protobuf.ByteString.copyFromUtf8(
//                (java.lang.String) ref);
//        accessToken_ = b;
//        return b;
//      } else {
//        return (com.google.protobuf.ByteString) ref;
//      }
//    }
//
//    public static final int USERNAME_FIELD_NUMBER = 2;
//    private volatile java.lang.Object username_;
//    /**
//     * <code>string username = 2;</code>
//     * @return The username.
//     */
//    @java.lang.Override
//    public java.lang.String getUsername() {
//      java.lang.Object ref = username_;
//      if (ref instanceof java.lang.String) {
//        return (java.lang.String) ref;
//      } else {
//        com.google.protobuf.ByteString bs =
//            (com.google.protobuf.ByteString) ref;
//        java.lang.String s = bs.toStringUtf8();
//        username_ = s;
//        return s;
//      }
//    }
//    /**
//     * <code>string username = 2;</code>
//     * @return The bytes for username.
//     */
//    @java.lang.Override
//    public com.google.protobuf.ByteString
//        getUsernameBytes() {
//      java.lang.Object ref = username_;
//      if (ref instanceof java.lang.String) {
//        com.google.protobuf.ByteString b =
//            com.google.protobuf.ByteString.copyFromUtf8(
//                (java.lang.String) ref);
//        username_ = b;
//        return b;
//      } else {
//        return (com.google.protobuf.ByteString) ref;
//      }
//    }
//
//    private byte memoizedIsInitialized = -1;
//    @java.lang.Override
//    public final boolean isInitialized() {
//      byte isInitialized = memoizedIsInitialized;
//      if (isInitialized == 1) return true;
//      if (isInitialized == 0) return false;
//
//      memoizedIsInitialized = 1;
//      return true;
//    }
//
//    @java.lang.Override
//    public void writeTo(com.google.protobuf.CodedOutputStream output)
//                        throws java.io.IOException {
//      if (!getAccessTokenBytes().isEmpty()) {
//        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, accessToken_);
//      }
//      if (!getUsernameBytes().isEmpty()) {
//        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, username_);
//      }
//      unknownFields.writeTo(output);
//    }
//
//    @java.lang.Override
//    public int getSerializedSize() {
//      int size = memoizedSize;
//      if (size != -1) return size;
//
//      size = 0;
//      if (!getAccessTokenBytes().isEmpty()) {
//        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, accessToken_);
//      }
//      if (!getUsernameBytes().isEmpty()) {
//        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, username_);
//      }
//      size += unknownFields.getSerializedSize();
//      memoizedSize = size;
//      return size;
//    }
//
//    @java.lang.Override
//    public boolean equals(final java.lang.Object obj) {
//      if (obj == this) {
//       return true;
//      }
//      if (!(obj instanceof com.evol.protobuf.domain.MessageUser.MessageUserLogin)) {
//        return super.equals(obj);
//      }
//      com.evol.protobuf.domain.MessageUser.MessageUserLogin other = (com.evol.protobuf.domain.MessageUser.MessageUserLogin) obj;
//
//      if (!getAccessToken()
//          .equals(other.getAccessToken())) return false;
//      if (!getUsername()
//          .equals(other.getUsername())) return false;
//      if (!unknownFields.equals(other.unknownFields)) return false;
//      return true;
//    }
//
//    @java.lang.Override
//    public int hashCode() {
//      if (memoizedHashCode != 0) {
//        return memoizedHashCode;
//      }
//      int hash = 41;
//      hash = (19 * hash) + getDescriptor().hashCode();
//      hash = (37 * hash) + ACCESS_TOKEN_FIELD_NUMBER;
//      hash = (53 * hash) + getAccessToken().hashCode();
//      hash = (37 * hash) + USERNAME_FIELD_NUMBER;
//      hash = (53 * hash) + getUsername().hashCode();
//      hash = (29 * hash) + unknownFields.hashCode();
//      memoizedHashCode = hash;
//      return hash;
//    }
//
//    public static com.evol.protobuf.domain.MessageUser.MessageUserLogin parseFrom(
//        java.nio.ByteBuffer data)
//        throws com.google.protobuf.InvalidProtocolBufferException {
//      return PARSER.parseFrom(data);
//    }
//    public static com.evol.protobuf.domain.MessageUser.MessageUserLogin parseFrom(
//        java.nio.ByteBuffer data,
//        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//        throws com.google.protobuf.InvalidProtocolBufferException {
//      return PARSER.parseFrom(data, extensionRegistry);
//    }
//    public static com.evol.protobuf.domain.MessageUser.MessageUserLogin parseFrom(
//        com.google.protobuf.ByteString data)
//        throws com.google.protobuf.InvalidProtocolBufferException {
//      return PARSER.parseFrom(data);
//    }
//    public static com.evol.protobuf.domain.MessageUser.MessageUserLogin parseFrom(
//        com.google.protobuf.ByteString data,
//        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//        throws com.google.protobuf.InvalidProtocolBufferException {
//      return PARSER.parseFrom(data, extensionRegistry);
//    }
//    public static com.evol.protobuf.domain.MessageUser.MessageUserLogin parseFrom(byte[] data)
//        throws com.google.protobuf.InvalidProtocolBufferException {
//      return PARSER.parseFrom(data);
//    }
//    public static com.evol.protobuf.domain.MessageUser.MessageUserLogin parseFrom(
//        byte[] data,
//        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//        throws com.google.protobuf.InvalidProtocolBufferException {
//      return PARSER.parseFrom(data, extensionRegistry);
//    }
//    public static com.evol.protobuf.domain.MessageUser.MessageUserLogin parseFrom(java.io.InputStream input)
//        throws java.io.IOException {
//      return com.google.protobuf.GeneratedMessageV3
//          .parseWithIOException(PARSER, input);
//    }
//    public static com.evol.protobuf.domain.MessageUser.MessageUserLogin parseFrom(
//        java.io.InputStream input,
//        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//        throws java.io.IOException {
//      return com.google.protobuf.GeneratedMessageV3
//          .parseWithIOException(PARSER, input, extensionRegistry);
//    }
//    public static com.evol.protobuf.domain.MessageUser.MessageUserLogin parseDelimitedFrom(java.io.InputStream input)
//        throws java.io.IOException {
//      return com.google.protobuf.GeneratedMessageV3
//          .parseDelimitedWithIOException(PARSER, input);
//    }
//    public static com.evol.protobuf.domain.MessageUser.MessageUserLogin parseDelimitedFrom(
//        java.io.InputStream input,
//        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//        throws java.io.IOException {
//      return com.google.protobuf.GeneratedMessageV3
//          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
//    }
//    public static com.evol.protobuf.domain.MessageUser.MessageUserLogin parseFrom(
//        com.google.protobuf.CodedInputStream input)
//        throws java.io.IOException {
//      return com.google.protobuf.GeneratedMessageV3
//          .parseWithIOException(PARSER, input);
//    }
//    public static com.evol.protobuf.domain.MessageUser.MessageUserLogin parseFrom(
//        com.google.protobuf.CodedInputStream input,
//        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//        throws java.io.IOException {
//      return com.google.protobuf.GeneratedMessageV3
//          .parseWithIOException(PARSER, input, extensionRegistry);
//    }
//
//    @java.lang.Override
//    public Builder newBuilderForType() { return newBuilder(); }
//    public static Builder newBuilder() {
//      return DEFAULT_INSTANCE.toBuilder();
//    }
//    public static Builder newBuilder(com.evol.protobuf.domain.MessageUser.MessageUserLogin prototype) {
//      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
//    }
//    @java.lang.Override
//    public Builder toBuilder() {
//      return this == DEFAULT_INSTANCE
//          ? new Builder() : new Builder().mergeFrom(this);
//    }
//
//    @java.lang.Override
//    protected Builder newBuilderForType(
//        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
//      Builder builder = new Builder(parent);
//      return builder;
//    }
//    /**
//     * Protobuf type {@code MessageUserLogin}
//     */
//    public static final class Builder extends
//        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
//        // @@protoc_insertion_point(builder_implements:MessageUserLogin)
//        com.evol.protobuf.domain.MessageUser.MessageUserLoginOrBuilder {
//      public static final com.google.protobuf.Descriptors.Descriptor
//          getDescriptor() {
//        return com.evol.protobuf.domain.MessageUser.internal_static_MessageUserLogin_descriptor;
//      }
//
//      @java.lang.Override
//      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
//          internalGetFieldAccessorTable() {
//        return com.evol.protobuf.domain.MessageUser.internal_static_MessageUserLogin_fieldAccessorTable
//            .ensureFieldAccessorsInitialized(
//                com.evol.protobuf.domain.MessageUser.MessageUserLogin.class, com.evol.protobuf.domain.MessageUser.MessageUserLogin.Builder.class);
//      }
//
//      // Construct using com.evol.protobuf.domain.MessageUser.MessageUserLogin.newBuilder()
//      private Builder() {
//        maybeForceBuilderInitialization();
//      }
//
//      private Builder(
//          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
//        super(parent);
//        maybeForceBuilderInitialization();
//      }
//      private void maybeForceBuilderInitialization() {
//        if (com.google.protobuf.GeneratedMessageV3
//                .alwaysUseFieldBuilders) {
//        }
//      }
//      @java.lang.Override
//      public Builder clear() {
//        super.clear();
//        accessToken_ = "";
//
//        username_ = "";
//
//        return this;
//      }
//
//      @java.lang.Override
//      public com.google.protobuf.Descriptors.Descriptor
//          getDescriptorForType() {
//        return com.evol.protobuf.domain.MessageUser.internal_static_MessageUserLogin_descriptor;
//      }
//
//      @java.lang.Override
//      public com.evol.protobuf.domain.MessageUser.MessageUserLogin getDefaultInstanceForType() {
//        return com.evol.protobuf.domain.MessageUser.MessageUserLogin.getDefaultInstance();
//      }
//
//      @java.lang.Override
//      public com.evol.protobuf.domain.MessageUser.MessageUserLogin build() {
//        com.evol.protobuf.domain.MessageUser.MessageUserLogin result = buildPartial();
//        if (!result.isInitialized()) {
//          throw newUninitializedMessageException(result);
//        }
//        return result;
//      }
//
//      @java.lang.Override
//      public com.evol.protobuf.domain.MessageUser.MessageUserLogin buildPartial() {
//        com.evol.protobuf.domain.MessageUser.MessageUserLogin result = new com.evol.protobuf.domain.MessageUser.MessageUserLogin(this);
//        result.accessToken_ = accessToken_;
//        result.username_ = username_;
//        onBuilt();
//        return result;
//      }
//
//      @java.lang.Override
//      public Builder clone() {
//        return super.clone();
//      }
//      @java.lang.Override
//      public Builder setField(
//          com.google.protobuf.Descriptors.FieldDescriptor field,
//          java.lang.Object value) {
//        return super.setField(field, value);
//      }
//      @java.lang.Override
//      public Builder clearField(
//          com.google.protobuf.Descriptors.FieldDescriptor field) {
//        return super.clearField(field);
//      }
//      @java.lang.Override
//      public Builder clearOneof(
//          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
//        return super.clearOneof(oneof);
//      }
//      @java.lang.Override
//      public Builder setRepeatedField(
//          com.google.protobuf.Descriptors.FieldDescriptor field,
//          int index, java.lang.Object value) {
//        return super.setRepeatedField(field, index, value);
//      }
//      @java.lang.Override
//      public Builder addRepeatedField(
//          com.google.protobuf.Descriptors.FieldDescriptor field,
//          java.lang.Object value) {
//        return super.addRepeatedField(field, value);
//      }
//      @java.lang.Override
//      public Builder mergeFrom(com.google.protobuf.Message other) {
//        if (other instanceof com.evol.protobuf.domain.MessageUser.MessageUserLogin) {
//          return mergeFrom((com.evol.protobuf.domain.MessageUser.MessageUserLogin)other);
//        } else {
//          super.mergeFrom(other);
//          return this;
//        }
//      }
//
//      public Builder mergeFrom(com.evol.protobuf.domain.MessageUser.MessageUserLogin other) {
//        if (other == com.evol.protobuf.domain.MessageUser.MessageUserLogin.getDefaultInstance()) return this;
//        if (!other.getAccessToken().isEmpty()) {
//          accessToken_ = other.accessToken_;
//          onChanged();
//        }
//        if (!other.getUsername().isEmpty()) {
//          username_ = other.username_;
//          onChanged();
//        }
//        this.mergeUnknownFields(other.unknownFields);
//        onChanged();
//        return this;
//      }
//
//      @java.lang.Override
//      public final boolean isInitialized() {
//        return true;
//      }
//
//      @java.lang.Override
//      public Builder mergeFrom(
//          com.google.protobuf.CodedInputStream input,
//          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//          throws java.io.IOException {
//        com.evol.protobuf.domain.MessageUser.MessageUserLogin parsedMessage = null;
//        try {
//          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
//        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
//          parsedMessage = (com.evol.protobuf.domain.MessageUser.MessageUserLogin) e.getUnfinishedMessage();
//          throw e.unwrapIOException();
//        } finally {
//          if (parsedMessage != null) {
//            mergeFrom(parsedMessage);
//          }
//        }
//        return this;
//      }
//
//      private java.lang.Object accessToken_ = "";
//      /**
//       * <code>string access_token = 1;</code>
//       * @return The accessToken.
//       */
//      public java.lang.String getAccessToken() {
//        java.lang.Object ref = accessToken_;
//        if (!(ref instanceof java.lang.String)) {
//          com.google.protobuf.ByteString bs =
//              (com.google.protobuf.ByteString) ref;
//          java.lang.String s = bs.toStringUtf8();
//          accessToken_ = s;
//          return s;
//        } else {
//          return (java.lang.String) ref;
//        }
//      }
//      /**
//       * <code>string access_token = 1;</code>
//       * @return The bytes for accessToken.
//       */
//      public com.google.protobuf.ByteString
//          getAccessTokenBytes() {
//        java.lang.Object ref = accessToken_;
//        if (ref instanceof String) {
//          com.google.protobuf.ByteString b =
//              com.google.protobuf.ByteString.copyFromUtf8(
//                  (java.lang.String) ref);
//          accessToken_ = b;
//          return b;
//        } else {
//          return (com.google.protobuf.ByteString) ref;
//        }
//      }
//      /**
//       * <code>string access_token = 1;</code>
//       * @param value The accessToken to set.
//       * @return This builder for chaining.
//       */
//      public Builder setAccessToken(
//          java.lang.String value) {
//        if (value == null) {
//    throw new NullPointerException();
//  }
//
//        accessToken_ = value;
//        onChanged();
//        return this;
//      }
//      /**
//       * <code>string access_token = 1;</code>
//       * @return This builder for chaining.
//       */
//      public Builder clearAccessToken() {
//
//        accessToken_ = getDefaultInstance().getAccessToken();
//        onChanged();
//        return this;
//      }
//      /**
//       * <code>string access_token = 1;</code>
//       * @param value The bytes for accessToken to set.
//       * @return This builder for chaining.
//       */
//      public Builder setAccessTokenBytes(
//          com.google.protobuf.ByteString value) {
//        if (value == null) {
//    throw new NullPointerException();
//  }
//  checkByteStringIsUtf8(value);
//
//        accessToken_ = value;
//        onChanged();
//        return this;
//      }
//
//      private java.lang.Object username_ = "";
//      /**
//       * <code>string username = 2;</code>
//       * @return The username.
//       */
//      public java.lang.String getUsername() {
//        java.lang.Object ref = username_;
//        if (!(ref instanceof java.lang.String)) {
//          com.google.protobuf.ByteString bs =
//              (com.google.protobuf.ByteString) ref;
//          java.lang.String s = bs.toStringUtf8();
//          username_ = s;
//          return s;
//        } else {
//          return (java.lang.String) ref;
//        }
//      }
//      /**
//       * <code>string username = 2;</code>
//       * @return The bytes for username.
//       */
//      public com.google.protobuf.ByteString
//          getUsernameBytes() {
//        java.lang.Object ref = username_;
//        if (ref instanceof String) {
//          com.google.protobuf.ByteString b =
//              com.google.protobuf.ByteString.copyFromUtf8(
//                  (java.lang.String) ref);
//          username_ = b;
//          return b;
//        } else {
//          return (com.google.protobuf.ByteString) ref;
//        }
//      }
//      /**
//       * <code>string username = 2;</code>
//       * @param value The username to set.
//       * @return This builder for chaining.
//       */
//      public Builder setUsername(
//          java.lang.String value) {
//        if (value == null) {
//    throw new NullPointerException();
//  }
//
//        username_ = value;
//        onChanged();
//        return this;
//      }
//      /**
//       * <code>string username = 2;</code>
//       * @return This builder for chaining.
//       */
//      public Builder clearUsername() {
//
//        username_ = getDefaultInstance().getUsername();
//        onChanged();
//        return this;
//      }
//      /**
//       * <code>string username = 2;</code>
//       * @param value The bytes for username to set.
//       * @return This builder for chaining.
//       */
//      public Builder setUsernameBytes(
//          com.google.protobuf.ByteString value) {
//        if (value == null) {
//    throw new NullPointerException();
//  }
//  checkByteStringIsUtf8(value);
//
//        username_ = value;
//        onChanged();
//        return this;
//      }
//      @java.lang.Override
//      public final Builder setUnknownFields(
//          final com.google.protobuf.UnknownFieldSet unknownFields) {
//        return super.setUnknownFields(unknownFields);
//      }
//
//      @java.lang.Override
//      public final Builder mergeUnknownFields(
//          final com.google.protobuf.UnknownFieldSet unknownFields) {
//        return super.mergeUnknownFields(unknownFields);
//      }
//
//
//      // @@protoc_insertion_point(builder_scope:MessageUserLogin)
//    }
//
//    // @@protoc_insertion_point(class_scope:MessageUserLogin)
//    private static final com.evol.protobuf.domain.MessageUser.MessageUserLogin DEFAULT_INSTANCE;
//    static {
//      DEFAULT_INSTANCE = new com.evol.protobuf.domain.MessageUser.MessageUserLogin();
//    }
//
//    public static com.evol.protobuf.domain.MessageUser.MessageUserLogin getDefaultInstance() {
//      return DEFAULT_INSTANCE;
//    }
//
//    private static final com.google.protobuf.Parser<MessageUserLogin>
//        PARSER = new com.google.protobuf.AbstractParser<MessageUserLogin>() {
//      @java.lang.Override
//      public MessageUserLogin parsePartialFrom(
//          com.google.protobuf.CodedInputStream input,
//          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//          throws com.google.protobuf.InvalidProtocolBufferException {
//        return new MessageUserLogin(input, extensionRegistry);
//      }
//    };
//
//    public static com.google.protobuf.Parser<MessageUserLogin> parser() {
//      return PARSER;
//    }
//
//    @java.lang.Override
//    public com.google.protobuf.Parser<MessageUserLogin> getParserForType() {
//      return PARSER;
//    }
//
//    @java.lang.Override
//    public com.evol.protobuf.domain.MessageUser.MessageUserLogin getDefaultInstanceForType() {
//      return DEFAULT_INSTANCE;
//    }
//
//  }
//
//  private static final com.google.protobuf.Descriptors.Descriptor
//    internal_static_MessageUserLogin_descriptor;
//  private static final
//    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
//      internal_static_MessageUserLogin_fieldAccessorTable;
//
//  public static com.google.protobuf.Descriptors.FileDescriptor
//      getDescriptor() {
//    return descriptor;
//  }
//  private static  com.google.protobuf.Descriptors.FileDescriptor
//      descriptor;
//  static {
//    java.lang.String[] descriptorData = {
//      "\n\034com/evol/protobuf/user.proto\":\n\020Messag" +
//      "eUserLogin\022\024\n\014access_token\030\001 \001(\t\022\020\n\010user" +
//      "name\030\002 \001(\tB\'\n\030com.evol.protobuf.domainB\013" +
//      "MessageUserb\006proto3"
//    };
//    descriptor = com.google.protobuf.Descriptors.FileDescriptor
//      .internalBuildGeneratedFileFrom(descriptorData,
//        new com.google.protobuf.Descriptors.FileDescriptor[] {
//        });
//    internal_static_MessageUserLogin_descriptor =
//      getDescriptor().getMessageTypes().get(0);
//    internal_static_MessageUserLogin_fieldAccessorTable = new
//      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
//        internal_static_MessageUserLogin_descriptor,
//        new java.lang.String[] { "AccessToken", "Username", });
//  }
//
//  // @@protoc_insertion_point(outer_class_scope)
//}
