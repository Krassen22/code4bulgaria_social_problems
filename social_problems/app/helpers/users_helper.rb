module UsersHelper

  def devise_error_messages_c!
    return "" if resource.errors.empty?

    messages = resource.errors.full_messages.map { |msg| content_tag(:li, msg) }.join
    html = <<-HTML
    <div class="alert alert-warning">
      <ul>#{messages}</ul>
    </div>
    HTML

    html.html_safe
  end

end