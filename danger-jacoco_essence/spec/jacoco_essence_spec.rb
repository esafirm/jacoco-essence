require File.expand_path("../spec_helper", __FILE__)

module Danger
  describe Danger::DangerJacocoEssence do
    it "should be a plugin" do
      expect(Danger::DangerJacocoEssence.new(nil)).to be_a Danger::Plugin
    end

    #
    # You should test your custom attributes and methods here
    #
    describe "with Dangerfile" do
      before do
        @dangerfile = testing_dangerfile
        @jplugin = @dangerfile.jacoco_essence

        # mock the PR data
        # you can then use this, eg. github.pr_author, later in the spec
        # json = File.read(File.dirname(__FILE__) + '/support/fixtures/github_pr.json') # example json: `curl https://api.github.com/repos/danger/danger-plugin-template/pulls/18 > github_pr.json`
        # allow(@jplugin.github).to receive(:pr_json).and_return(json)
      end

      # Some examples for writing tests
      # You should replace these with your own.

      it "Run the JAR" do 
        xmlReport = "#{File.dirname(__FILE__)}/fixtures/b.xml"
        @jplugin.report(xmlReport)
      end

    end
  end
end
